def fib(n):
  a=0
  b=1
  for i in range(n//2):
    a=a+b
    b=b+a

  return b if n%2 else a;

print(fib(9))
