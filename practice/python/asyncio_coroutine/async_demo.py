import socket
import time

from selectors import DefaultSelector, EVENT_WRITE, EVENT_READ

start = time.time()
selector = DefaultSelector()
n_jobs = 0

class Future:
    def __init__(self):
        self.callbacks = []

    def resolve(self):
        for fn in self.callbacks:
            fn()

class Task:
    def __init__(self, coro):
        self.coro = coro
        self.step()

    def step(self):
        try:
            future = next(self.coro)
        except StopIteration:
            return
        future.callbacks.append(self.step)


def get(path):
    global n_jobs
    n_jobs+=1
    s = socket.socket()
    s.setblocking(False)
    try:
        s.connect(('localhost', 5000))
    except BlockingIOError:
        pass

    f = Future()
    selector.register(s.fileno(), EVENT_WRITE, f)
    yield f

    selector.unregister(s.fileno())
    request = 'GET %s HTTP/1.0\r\n\r\n' % path
    s.send(request.encode())

    buf = []
    callback = lambda: readable(s, buf)
    f = Future()
    f.callbacks.append(callback)
    selector.register(s.fileno(), EVENT_READ, f)

def readable(s, buf):
    global n_jobs
    selector.unregister(s.fileno())
    chunk = s.recv(1000)
    if chunk:
        buf.append(chunk)
        callback = lambda: readable(s, buf)
        f = Future()
        f.callbacks.append(callback)
        selector.register(s.fileno(), EVENT_READ, f)
    else:
        body = b''.join(buf).decode()
        print(body.split('\n')[0])
        n_jobs-=1
        

Task(get('/foo'))
Task(get('/bar'))

while n_jobs:
    events = selector.select()
    for key, mask in events:
        future = key.data
        future.resolve()

print('%.2f seconds' % (time.time() - start))
