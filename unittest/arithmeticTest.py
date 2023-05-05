a = 1                       # Integer types
b = "hello"                 # String type
c = [1,2,3]                 # List of ints type
d = 5.5                     # Float type
e = ["a", "b", "c"]         # List of str type
f = None                    # None type
g = True                    # Boolean type
j = {"a", "b", "c"}         # Set type
h = {"a":1, "b":2, "c":3}   # Mapping type
i = (1, 2, 3)               # Tuple type
p = (1, "two", 3.4)         # Tuple type

#addition
x = a + 1       #expected output: OKAY
y = a + b       #ERROR: Unsupported operand types for + ("int" and "str")
z = a + c       #ERROR: Unsupported operand types for + ("int" and "List[int]")
w = a + f       #ERROR: Unsupported operand types for + ("int" and "None")
v = b + e       #ERROR: Unsupported operand types for + ("str" and "List[str]")
u = b + d       #ERROR: Unsupported operand types for + ("str" and "float")
r = c + e       #expected output: OKAY
s = e + f       #ERROR: No overload variant of "__add__" of "list" matches argument type "None"
t = f + g       #ERROR: Unsupported operand types for + ("None" and "bool")  [operator]
r = h + h       #ERROR: Unsupported left operand type for + ("Dict[str, int]")  [operator]
m = i + h       #ERROR: No overload variant of "__add__" of "tuple" matches argument type "Dict[str, int]"  [operator]
n = j + j       #ERROR: Unsupported left operand type for + ("Set[str]")  [operator]
q = j + h       #ERROR: Unsupported left operand type for + ("Set[str]")  [operator]
aa = p + i      #expected output: OKAY
bb = p + j      #ERROR: No overload variant of "__add__" of "tuple" matches argument type "Set[str]"  [operator]


#subtraction
x1 = a - 1       #expected output: OKAY
y1 = a - b       #ERROR: Unsupported operand types for - ("int" and "str")
z1 = a - c       #ERROR: Unsupported operand types for - ("int" and "List[int]")
w1 = a - f       #ERROR: Unsupported operand types for - ("int" and "None")
v1 = b - e       #ERROR: Unsupported left operand type for - ("str")
u1 = b - d       #ERROR: Unsupported operand types for - ("str" and "float")
r1 = c - e       #ERROR: Unsupported left operand type for - ("List[int]")
s1 = e - f       #ERROR: Unsupported left operand type for - ("List[str]")
t1 = f - g       #ERROR: Unsupported operand types for - ("None" and "bool")  [operator]
r1 = h - h       #ERROR: Unsupported left operand type for - ("Dict[str, int]")  [operator]
m1 = i - h       #ERROR: Unsupported left operand type for - ("Tuple[int, int, int]")  [operator]
n1 = j - j       #expected output: OKAY
q1 = j - h       #ERROR: Unsupported operand types for - ("Set[str]" and "Dict[str, int]")  [operator]
aa1 = p - i      #ERROR: Unsupported left operand type for - ("Tuple[int, str, float]")  [operator]
bb = p - j       #ERROR:Unsupported left operand type for - ("Tuple[int, str, float]")  [operator]

#multiplication
x2 = a * 2       #expected output: OKAY
y2 = a * b       #expected output: OKAY
z2 = a * c       #expected output: OKAY
w2 = a * f       #ERROR: Unsupported operand types for * ("int" and "None")
v2 = b * e       #ERROR: Unsupported operand types for * ("str" and "List[str]")
u2 = b * d       #ERROR: Unsupported operand types for * ("str" and "float") 
r2 = c * e       #ERROR: Unsupported operand types for * ("List[int]" and "List[str]")
s2 = e * f       #ERROR: Unsupported operand types for * ("List[str]" and "None")
t2 = f * g       #ERROR: Unsupported operand types for * ("None" and "bool")  [operator]
r2 = h * h       #ERROR: Unsupported left operand type for * ("Dict[str, int]")  [operator]
m2 = i * h       #ERROR: Unsupported operand types for * ("Tuple[int, int, int]" and "Dict[str, int]")  [operator]
n2 = j * j       #ERROR: Unsupported left operand type for * ("Set[str]")  [operator]
q2 = j * h       #ERROR: Unsupported left operand type for * ("Set[str]")  [operator]
aa2 = p * i      #ERROR: Unsupported operand types for * ("Tuple[int, str, float]" and "Tuple[int, int, int]")  [operator]
bb2 = p * j      #ERROR: Unsupported operand types for * ("Tuple[int, str, float]" and "Set[str]")  [operator]

#division
x3 = a / 2       #expected output: OKAY
y3 = a / b       #ERROR: Unsupported operand types for / ("int" and "str")
z3 = a / c       #ERROR: Unsupported operand types for / ("int" and "List[int]")
w3 = a / f       #ERROR: Unsupported operand types for / ("int" and "None")
v3 = b / e       #ERROR: Unsupported left operand type for / ("str")
u3 = b / d       #ERROR: Unsupported operand types for / ("str" and "float")
r3 = c / e       #ERROR: Unsupported left operand type for / ("List[int]")
s3 = e / f       #ERROR: Unsupported left operand type for / ("List[str]")
t3 = f / g       #ERROR: Unsupported operand types for / ("None" and "bool")  [operator]
r3 = h / h       #ERROR: Unsupported left operand type for / ("Dict[str, int]")  [operator]
m3 = i / h       #ERROR: Unsupported left operand type for / ("Tuple[int, int, int]")  [operator]
n3 = j / j       #ERROR: Unsupported left operand type for / ("Set[str]")  [operator]
q3 = j / h       #ERROR: Unsupported left operand type for / ("Set[str]")  [operator]
aa3 = p / i      #ERROR: Unsupported left operand type for / ("Tuple[int, str, float]")  [operator]
bb3 = p / j      #ERROR: Unsupported left operand type for / ("Tuple[int, str, float]")  [operator]


#power
x4 = a ** 2     #expected output: OKAY
y4 = b ** 2     #ERROR: Unsupported operand types for ** ("str" and "int") 
z4 = c ** 2     #ERROR: Unsupported operand types for ** ("List[int]" and "int")
w4 = d ** 2     #expected output: OKAY
v4 = e ** 2     #ERROR: Unsupported operand types for ** ("List[str]" and "int")
u4 = f ** 2     #ERROR: Unsupported operand types for ** ("None" and "int")
t4 = g ** 2     #expected output: OKAY
r4 = h ** 2     #ERROR: Unsupported operand types for ** ("Dict[str, int]" and "int")  [operator]
m4 = i ** 2     #ERROR: Unsupported operand types for ** ("Tuple[int, int, int]" and "int")  [operator]
n4 = j ** 2     #ERROR: Unsupported operand types for ** ("Set[str]" and "int")  [operator]
aa4 = p ** 2    #ERROR:Unsupported operand types for ** ("Tuple[int, str, float]" and "int")  [operator]

