# VARIABLE ANNOTATIONS

# Numeric types
i: int = 1                                      # Expected output: OKAY
f: float; f = 2.0                               # Expected output: OKAY
c: complex = 1 + 2j                             # Expected output: OKAY

# Text type
word: str = "string"                            # Expected output: OKAY

# List types
l1: list[int] = [1, 2, 3]                       # Expected output: OKAY
# Nested list types
l2: list[list[int]] = [[1, 2, 3], [4, 5, 6]]    # Expected output: OKAY

# Tuple types
t: tuple[str, str, str] = ('a', 'b', 'c')      # Expected output: OKAY

# Range type
r: range = range(3)                             # Expected output: OKAY

# Mapping type
m: dict[int, str] = {1: 'a', 3: 'b'}           # Expected output: OKAY

# Set types
s: set = {1, 2, 3}                             # Expected output: OKAY

# Boolean type
b: bool = False                                # Expected output: OKAY

# NoneTypes
nothing: None = None                            # Expected output: OKAY


# FUNCTION DEFINITIONS
def getIntOK() -> int:                          # Expected output: OKAY
    return i
def getIntError() -> int:                       # ERROR: Incompatible return value type (got "float", expected "int")  [return-value]
    return f

def getFloatOK() -> float:                      # Expected output: OKAY
    return f
def getFloatError() -> float:                   # ERROR: Incompatible return value type (got "complex", expected "float")  [return-value]
    return c

def getComplexOK() -> complex:                  # Expected output: OKAY
    return c
def getComplexError() -> complex:               # ERROR: Incompatible return value type (got "str", expected "complex")  [return-value]
    return word

def getStrOK() -> str:                          # Expected output: OKAY
    return word
def getStrError() -> str:                       # ERROR: Incompatible return value type (got "int", expected "str")  [return-value]
    return i

def getListOK() -> list[int]:                   # Expected output: OKAY
    return l1
def getListError() -> list[int]:                # ERROR: List item 0-2 has incompatible type "str"; expected "int"  [list-item]
    return ['a', 'b', 'c']

def getNestedListOK() -> list[list[int]]:       # Expected output: OKAY
    return l2
def getNestedListError() -> list[list[int]]:    # ERROR: Incompatible return value type (got "Tuple[List[int], ...]", expected "List[List[int]]")  [return-value]
    return tuple(l2)

def getTupleOK() -> tuple[str, str, str]:       # Expected output: OKAY
    return t
def getTupleError() -> tuple[str, str, str]:    # ERROR: Incompatible return value type (got "List[str]", expected "Tuple[str, str, str]")  [return-value]
    return list(t)

def getRangeOK() -> range:                      # Expected output: OKAY
    return r
def getRangeError() -> range:                   # ERROR: Incompatible return value type (got "int", expected "range")  [return-value]
    return i

def getDictOK() -> dict[int, str]:              # Expected output: OKAY
    return m
def getDictError() -> dict[int, str]:           # ERROR: Incompatible return value type (got "List[int]", expected "Dict[int, str]")  [return-value]
    return [1, 2, 3]

def getSetOK() -> set:                          # Expected output: OKAY
    return s
def getSetError() -> set:                       # ERROR: Incompatible return value type (got "List[int]", expected "Set[Any]")  [return-value]
    return [1, 2, 3]

def getBoolOK() -> bool:                        # Expected output: OKAY
    return not b
def getBoolError() -> bool:                     # ERROR: Incompatible return value type (got "int", expected "bool")  [return-value]
    return i

def getNoneOK() -> None:                        # Expected output: OKAY
    return nothing
def getNoneError() -> None:                     # ERROR: No return value expected  [return-value]
    return i