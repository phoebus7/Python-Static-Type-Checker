a = 1                       # Integer types
b = "hello"                 # String type
c = [1,2,3]                 # List of ints type
d = 5.5                     # Float type
e = ["a", "b", "c"]         # List of str type
f = None                    # None type
g = {"a", "b", "c"}			# Set type
h = {"a":1, "b":2, "c":3}	# Mapping type
i = (1, 2, 3)				# Tuple type
m = True					# Boolean type

#forLoop
for x in range(a):          				#expected output: OKAY
	for y in range(b):      				#ERROR: No overload variant of "range" matches argument type "str" [call-overload]
		for z in c:            				#expected output: OKAY
			for w in b:        				#expected output: OKAY
				for v in f:					#ERROR: "None" has no attribute "__iter__" (not iterable)  [attr-defined]
					for s in range(d):		#ERROR: No overload variant of "range" matches argument type "float"  [call-overload]
						for n in m:			#ERROR:"bool" has no attribute "__iter__" (not iterable)  [attr-defined]
							t = 10			
						t = 6
					t = 5
				t = 1
			t = 2
		t = 3
	t = 4

for j in h:									#expected output: OKAY
	t = 7
	for k in i:								#expected output: OKAY
		t = 8
		for l in g:							#expected output: OKAY
			t = 9



#whileLoop
while a > 5:			#expected output: OKAY
	t = 1
	while b <= 10:		#ERROR: Unsupported operand types for <= ("List[str]" and "int") 
		t = 2
		while f != 0:	#expected output: OKAY
			t = 3

