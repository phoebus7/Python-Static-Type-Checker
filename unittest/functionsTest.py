import asyncio

# GLOBAL VARIABLES TO BE USED IN TESTS
i, f, c = 1, 2.0, 1 + 2j                    # Numeric types
word = "string"                             # Text type
l, t, r = [1, 2, 3], (1, 2, 3), range(3)    # Sequence types
m = {1: 'a', 3: 'b'}                        # Mapping type
s = {1, 2, 3}                               # Set type
b = False                                   # Boolean type
nothing = None                              # NonyType


# FUNCTION DEFINITIONS TO BE USED IN TEST
def getAnyValue():                          # Expected output: OKAY
    return 13

def myDecorator(func):                      # Expected output: OKAY
    def wrapper():
        print("===============")
        func()
        print("===============")
    return wrapper

@myDecorator
def sayHello():                             # Expected output: OKAY
    print("Hello")

async def asyncTest():                      # Expected output: OKAY
    print('Hello ...')                      # to execute this function, use asyncio.run(asyncTest())
    await asyncio.sleep(1)
    print('... World!')

def calculateDiv(x, y):                     # Expected output: OKAY
    return x / y

def calculateDivWithDefault(x, y = 2):      # Expected output: OKAY
    return x / y

# TEST FOR POSITIONAL-ONLY ARGUMENTS
def customDivMod(a, b, /):                  # Expected output: OKAY
    return a // b, a % b

def customDivModWithDefault(a, b = 10, /):  # Expected output: OKAY
    return a // b, a % b

def argsFunc(*args):                        # Expected output: OKAY
    for elem in args:
        print(elem, end = " ")

def kwargsFunc(**kwargs):                   # Expected output: OKAY
    for key, value in kwargs.items():
        print(key, value, sep = ":", end = "; ")

def fibonacci(n):                           # Expected output: OKAY
    if n <= 1: return n
    else: return (fibonacci(n - 1) + fibonacci(n - 2))

# ACTUAL FUNCTION CALLS
print("==> getAnyValue called:", getAnyValue()) # Expected output: OKAY
print()

print("==> sayHello called:")
sayHello()                                  # Expected output: OKAY
print()

print("==> asyncTest called:")
asyncio.run(asyncTest())                    # Expected output: OKAY
print()

print("==> calculateDiv called:")
print(calculateDiv(1, 10))                  # Expected output: OKAY
print(calculateDiv(y = 1, x = 10))          # Expected output: OKAY
print()

print("==> calculateDivWithDefault called:")
print(calculateDivWithDefault(1))           # Expected output: OKAY
print(calculateDivWithDefault(1, 10))       # Expected output: OKAY
print()

print("==> customDivMod called:")
print(customDivMod(5, 2))                   # Expected output: OKAY
print(customDivMod(a = 5, b = 2))           # ERROR: Unexpected keyword argument "a" & "b" for "customDivMod"  [call-arg]
print()

print("==> customDivModWithDefault called:")
print(customDivModWithDefault(5))           # Expected output: OKAY
print(customDivModWithDefault(5, 2))        # Expected output: OKAY
print(customDivModWithDefault(a = 5))       # ERROR: Unexpected keyword argument "a" for "customDivModWithDefault"  [call-arg]
print()

print("==> argsFunc called:")
argsFunc(1, 2, 3, 4, 5)                     # Expected output: OKAY
argsFunc(list('STRING'))                    # Expected output: OKAY
print('\n')

print("==> kwargsFunc called:")
kwargsFunc(a = 1, b = 2, c = 3)             # Expected output: OKAY
print('\n')

print("==> fibonacci called:")
fibonacci(5)                                # Expected output: OKAY
print('\n')

# ANY OTHER POTENTIAL FUNCTIONS ERRORS?