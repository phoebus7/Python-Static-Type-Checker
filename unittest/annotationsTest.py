import asyncio

# VARIABLE ANNOTATIONS
# Numeric types
i: int
i = 1                                           # Expected output: OKAY
j: int; j = 3.0                                 # ERROR: Incompatible types in assignment (expression has type "float", variable has type "int")  [assignment]
f: float; f = 2.0                               # Expected output: OKAY
c: complex = 1 + 2j                             # Expected output: OKAY

# Text type
word1: str = "string"                           # Expected output: OKAY
word2: str = 'string'                           # Expected output: OKAY
word3: str = 100                                # ERROR: Incompatible types in assignment (expression has type "int", variable has type "str")  [assignment]

# List types
l1: list[int] = [1, 2, 3]                       # Expected output: OKAY
l2: list[str] = ['a', 'b', 'c']                 # Expected output: OKAY
l3: list[str] = ['a', 'b', 3]                   # ERROR: List item 2 has incompatible type "int"; expected "str"  [list-item]
# Nested list types
l4: list[list[int]] = [[1, 2, 3], [4, 5, 6]]    # Expected output: OKAY
l5: list[list[int]] = [[1, 2, 3],
                       ['a', 'b', 'c']]         # ERROR:  List item 0/1/2 has incompatible type "str"; expected "int"  [list-item]

# Tuple types
t1: tuple[str, str, str] = ('a', 'b', 'c')      # Expected output: OKAY
t2: tuple[str] = ('a', 'b', 'c')                # ERROR: Incompatible types in assignment (expression has type "Tuple[str, str, str]", variable has type "Tuple[str]")  [assignment]

# Range type
r1: range = range(3)                            # Expected output: OKAY
r2: range = range(1, 10, 2)                     # Expected output: OKAY
r3: range = range(5.0)                          # ERROR: No overload variant of "range" matches argument type "float"  [call-overload]

# Mapping type
m1: dict[int, str] = {1: 'a', 3: 'b'}             # Expected output: OKAY
m2: dict[int, int] = {1: 1.0, 3: 3.0, 'c': 4.0}   # ERROR: Dict entry 0/1/2 has incompatible type "int": "float"; expected "int": "int"  [dict-item]
m3: dict[str, int] = {
    'kazakhstan': 100,
    'keniya': 2,
    'korea': 4
}                                               # Expected output: OKAY
m4: dict[str, str] = {
    'kazakhstan': 100,
    'keniya': 2,
    'korea': 4
}                                               # ERROR: Dict entry 0/1/2 has incompatible type "str": "int"; expected "str": "str"  [dict-item]

# Set types
s1: set = {1, 2, 3}                             # Expected output: OKAY
s2: set[int] = {1, 2, 3, 'a'}                   # ERROR: Argument 4 to <set> has incompatible type "str"; expected "int"  [arg-type]

# Boolean type
b1: bool = False                                # Expected output: OKAY

# NoneTypes
nothing: None = None                            # Expected output: OKAY


# FUNCTION DEFINITIONS
def myDecorator(func):                          # Expected output: OKAY
    def wrapper():
        print("===============")
        func()
        print("===============")
    return wrapper

@myDecorator
def sayHello() -> None:                         # Expected output: OKAY
    print("Hello")

def getMessage1() -> str:                       # Expected output: OKAY
    return "Message"

def getMessage2(switch: bool) -> bool:          # ERROR: Incompatible return value type (got "str", expected "bool")  [return-value]
    if switch:
        return True
    else:
        return "Message"

def getBoolean1() -> bool:                      # ERROR: Incompatible return value type (got "int", expected "bool")  [return-value]
    return 1

def getBoolean2() -> bool:                      # ERROR: Incompatible return value type (got "None", expected "bool")  [return-value]
    return True

async def asyncTest1() -> None:                 # Expected output: OKAY
    print('Hello ...')                          # to execute this function, use asyncio.run(asyncTest())
    await asyncio.sleep(1)
    print('... World!')

async def asyncTest2() -> None:                 # ERROR: No return value expected  [return-value]
    await asyncio.sleep(1)
    return -1

def calculateDiv1(x: int, y: int) -> float:     # Expected output: OKAY
    return x / y

def calculateDiv2(x: int, y: int) -> int:       # Expected output: OKAY
    return int(x / y)

def calculateDiv3(x: int | float, y: int | float) -> float:    # Expected output: OKAY
    return x / y

def calculateDivWithDefault(x: int, y: int = 2) -> float:      # Expected output: OKAY
    return x / y

# TEST FOR POSITIONAL-ONLY ARGUMENTS
def customDivMod1(a: int, b: int, /) -> tuple:                  # Expected output: OKAY
    return a // b, a % b

def customDivMod2(a: int, b: int, /) -> tuple:                  # ERROR: Incompatible return value type (got "List[int]", expected "Tuple[Any, ...]")  [return-value]
    return [a // b, a % b]

def customDivModWithDefault(a: int, b: int = 10, /) -> tuple:  # Expected output: OKAY
    return a // b, a % b

def argsFunc1(*args: list) -> None:             # Expected output: OKAY
    for elem in args:
        print(elem, end = " ")

def argsFunc2(*args: tuple) -> None:            # Expected output: OKAY
    for elem in args:
        print(elem, end = " ")


# ACTUAL FUNCTION CALLS
print("==> getMessage functions called:")
print(getMessage1())                            # Expected output: OKAY
print(getMessage2(True))                        # Expected output: OKAY
print(getMessage2(1))                           # ERROR: Argument 1 to "getMessage2" has incompatible type "int"; expected "bool"  [arg-type]
print()

print("==> getBoolean functions called:")
print(getBoolean1())                            # Expected output: OKAY
print(getBoolean2())                            # Expected output: OKAY
print()

print("==> asyncTest functions called:")
asyncio.run(asyncTest1())                       # Expected output: OKAY
asyncio.run(asyncTest2())                       # Expected output: OKAY
print()

print("==> calculateDiv called:")
print(calculateDiv1(1, 10))                      # Expected output: OKAY
print(calculateDiv1(y = 1, x = 10))              # Expected output: OKAY
print(calculateDiv1(20.0, 4.0))                  # ERROR: Argument 1/2 to "calculateDiv" has incompatible type "float"; expected "int"  [arg-type]
print(calculateDiv2(len('Hello, World!'), 2))    # Expected output: OKAY
print(calculateDiv2(len('Hello, World!'), 4.0))  # ERROR: Argument 2 to "calculateDiv2" has incompatible type "float"; expected "int"  [arg-type]
print(calculateDiv2(int(4.0), int(.5)))          # Expected output: OKAY
print(calculateDiv3(len('Hello, World!'), 2.0))  # Expected output: OKAY
print(calculateDiv3(4.0, int(1.5)))              # Expected output: OKAY
print()

print("==> calculateDivWithDefault called:")
print(calculateDivWithDefault(1.0))              # ERROR: Argument 1 to "calculateDivWithDefault" has incompatible type "float"; expected "int"  [arg-type]
print(calculateDivWithDefault(1, 10.0))          # ERROR: Argument 2 to "calculateDivWithDefault" has incompatible type "float"; expected "int"  [arg-type]
print()

print("==> customDivMod called:")
print(customDivMod1(range(10), 4.0))            # ERROR: Argument 1 to "customDivMod" has incompatible type "range"; expected "int"  [arg-type]
                                                # ERROR: Argument 2 to "customDivMod" has incompatible type "float"; expected "int"  [arg-type]
print()

print("==> argsFunc functions called:")
argsFunc1(1, 2, 3, 4, 5)                        # ERROR: Argument 1-5 to "argsFunc1" has incompatible type "int"; expected "List[Any]"  [arg-type]
argsFunc1(list('STRING'))                       # Expected output: OKAY
argsFunc2(1, 2, 3, 4, 5)                        # ERROR: Argument 1-5 to "argsFunc2" has incompatible type "int"; expected "Tuple[Any, ...]"  [arg-type]
argsFunc2((1, 2, 3, 4, 5))                      # Expected output: OKAY
print('\n')