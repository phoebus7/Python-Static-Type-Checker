b = 3
def pr(a, b, c, d):
    a = 3
    b = 4
    c = 1
    if a == 3:
        a = 1

def pr(a: int, b: float, c: list, d: set) -> int:
    a = 2
def fun(a = 2, d = 3, c =4):
    a = 1
def fun(a, b, c, d, /, z = 2, x = 1, e =4):
    a = 1

def fun1(a = 1, b = 2, /, c=3, d=4):
    a = 2
    
def fun3(*args, **kwargs):
    a = 4 + 2