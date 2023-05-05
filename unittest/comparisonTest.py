a = 10              # Integer types
b = [1,2,3]         # List of ints type
c = "hello"         # String type
d = 5.5             # Float type
e = ["a", "b", "c"] # List of str type
f = None            # None type
g = True                    # Boolean type
j = {"a", "b", "c"}         # Set type
h = {"a":1, "b":2, "c":3}   # Mapping type
i = (1, 2, 3)               # Tuple type
q = (1, "two", 3.4)         # Tuple type

a > 5       #expected output: OKAY
a > c       #ERROR: Unsupported operand types for > ("int" and "str")
a > b       #ERROR: Unsupported operand types for > ("int" and "List[int]")
a > d       #expected output: OKAY
b > e       #ERROR: Unsupported operand types for > ("List[int]" and "List[str]")
b > a       #ERROR: Unsupported operand types for > ("List[int]" and "int")
b > c       #ERROR: Unsupported operand types for > ("List[int]" and "str") 
g > f       #ERROR: Unsupported operand types for > ("bool" and "None")  [operator]
j > h       #ERROR: Unsupported operand types for > ("Set[str]" and "Dict[str, int]")  [operator]
c >= e      #ERROR: Unsupported operand types for >= ("int" and "str")
c >= f      #ERROR: Unsupported operand types for >= ("str" and "None"
d >= c      #ERROR: Unsupported operand types for >= ("float" and "str")
d >= c      #ERROR: Unsupported operand types for >= ("float" and "str")
e >= f      #ERROR: Unsupported operand types for >= ("List[str]" and "None")
f >= a      #ERROR: Unsupported operand types for <= ("int" and "None")
i >= q      #expected output: OKAY
j >= q      #ERROR: Unsupported operand types for >= ("Set[str]" and "Tuple[int, str, float]")  [operator]



a < 5       #expected output: OKAY
a < c       #ERROR: Unsupported operand types for < ("int" and "str")
a < b       #ERROR: Unsupported operand types for < ("int" and "List[int]")
a < d       #expected output: OKAY
b < e       #ERROR: Unsupported operand types for < ("List[int]" and "List[str]")
b < a       #ERROR: Unsupported operand types for < ("List[int]" and "int")
b < c       #ERROR: Unsupported operand types for < ("List[int]" and "str") 
g < f       #ERROR: Unsupported operand types for < ("bool" and "None")  [operator]
j < h       #ERROR: Unsupported operand types for < ("Set[str]" and "Dict[str, int]")  [operator]
c <= e      #ERROR: Unsupported operand types for <= ("int" and "str")
c <= f      #ERROR: Unsupported operand types for <= ("str" and "None"
d <= c      #ERROR: Unsupported operand types for <= ("float" and "str")
d <= c      #ERROR: Unsupported operand types for <= ("float" and "str")
e <= f      #ERROR: Unsupported operand types for <= ("List[str]" and "None")
f <= a      #ERROR: Unsupported operand types for >= ("int" and "None")
i <= q      #expected output: OKAY
j <= q      #ERROR: Unsupported operand types for <= ("Set[str]" and "Tuple[int, str, float]")  [operator]


a == 5       #expected output: OKAY
a == c       #expected output: OKAY
a == b       #expected output: OKAY
a == d       #expected output: OKAY
b == e       #expected output: OKAY
b == a       #expected output: OKAY
b == c       #expected output: OKAY
g == f       #expected output: OKAY
j == h       #expected output: OKAY
c == e       #expected output: OKAY
c == f       #expected output: OKAY
d == c       #expected output: OKAY
d == c       #expected output: OKAY
e == f       #expected output: OKAY
f == a       #expected output: OKAY
i == q       #expected output: OKAY
j == q       #expected output: OKAY

a != 5       #expected output: OKAY
a != c       #expected output: OKAY
a != b       #expected output: OKAY
a != d       #expected output: OKAY
b != e       #expected output: OKAY
b != a       #expected output: OKAY
b != c       #expected output: OKAY
c != e       #expected output: OKAY
c != f       #expected output: OKAY
d != c       #expected output: OKAY
d != c       #expected output: OKAY
e != f       #expected output: OKAY
f != a       #expected output: OKAY
g != f       #expected output: OKAY 
j != h       #expected output: OKAY
i != q       #expected output: OKAY
j != q       #expected output: OKAY








