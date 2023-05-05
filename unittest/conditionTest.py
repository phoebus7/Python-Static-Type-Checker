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
q = (1, "two", 3.4)         # Tuple type

if a == True:           #expected output: OKAY
    if g:               #expected output: OKAY
        t = 1
    elif not g:         #expected output: OKAY
        t = 2
elif f:                 #expected output: OKAY
    t = 3
    if e == c:          #expected output: OKAY
        t = 4
    elif g == False:    #expected output: OKAY
        t = 5
elif j:                 #expected output: OKAY
    t = 9
    if h:               #expected output: OKAY
        t = 10
    elif not i:         #expected output: OKAY
        t = 11
        if q:           #expected output: OKAY
            t = 12
else:   
    if e:               #expected output: OKAY
        t = 7
    elif not c:         #expected output: OKAY
        t = 8
    t = 6