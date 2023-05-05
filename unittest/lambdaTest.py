
# GLOBAL VARIABLES TO BE USED IN TESTS
i, f, c = 1, 2.0, 1 + 2j                    # Numeric types
word = "string"                             # Text type
l, t, r = [1, 2, 3], (1, 2, 3), range(3)    # Sequence types
m = {1: 'a', 3: 'b'}                        # Mapping type
s = {1, 2, 3}                               # Set type
b = False                                   # Boolean type
nothing = None                              # NonyType

test = lambda a : a + word
test1 = lambda a, b: a + b + i
test2 = lambda a, b = f: a + b + i
test3 = lambda a, b, c= i: a/b/c

test(5)                                     # expected output: OKAY
test("letters")                             # expected output: OKAY

test1("word")                               # ERROR: Too few arguments  
test1(3, 4)                                 # expected output: OKAY
test1(1, "w")                               # expected output: OKAY
test1(1, 3.5)                               # expected output: OKAY
test1(word, word)                           # expected output: OKAY
test1(nothing, b)                           # expected output: OKAY

test2(i)                                    # expected output: OKAY
test2(f)                                    # expected output: OKAY
test2(c)                                    # expected output: OKAY
test2(word)                                 # expected output: OKAY
test2(l)                                    # expected output: OKAY
test2(t)                                    # expected output: OKAY
test2(i, c)                                 # expected output: OKAY
test2(i, word)                              # expected output: OKAY
test2(f, l)                                 # expected output: OKAY
test2(word, b)                              # expected output: OKAY

test3("word")                               # ERROR: Too few arguments  
test3(3, b=4)                               # expected output: OKAY
test3(a=3, b="w")                           # expected output: OKAY
test3(1, 3.5)                               # expected output: OKAY
test3(word, word)                           # expected output: OKAY
test3(nothing, b)                           # expected output: OKAY
