import trio
async def open_tcp_socket(hostname, port, *, timeout = 0.250):
    targets = await trio.socket.getaddrinfo(hostname, port, type=trio.socket.SOCK_STREAM)
    attempts_failed = [trio.Event() for _ in targets]

    winning_socket = None

    async def attempt(which, nursery):
        # wait for prev to fail or timeout
        if which > 0:
            with trio.move_on_after(timeout):
                await attempts_failed[which - 1].wait()
        # start attempt
        if which + 1 < len(targets):
            nursery.start_soon(attempt, which+1, nursery)

        # try connecting
        *socket_config, _, socket_target = targets[which]
        try:
            socket = trio.socket.socket(*socket_config)
            await socket.connect(socket_target)
        except OSError:
            attempts_failed[which].set()
        else:
            nursery.cancel_scope.cancel()
            nonlocal winning_socket
            winning_socket = socket
        # if fails, tell other
        # pass :
            # cancel others
            #return working socket

    async with trio.open_nursery() as nursery:
        nursery.start_soon(attempt, 0, nursery)
    

    if winning_socket is None:
        raise OSError("oops")
    else:
        return winning_socket

def main():
    print(trio.run(open_tcp_socket, "debian.com", 443))

if __name__ == "__main__":
    main()