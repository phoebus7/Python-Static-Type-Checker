five:int = 5
none: None = None
fourfive: float = 4.5
ten: int = 10
stringf: str = "f"
twofour: float = 2.4
stringk: str = "k"
intlist: list = [1,2,3]
strlist: list = ["s", "a", "p"]
homotuple: tuple = (1, 4, 1)
heterotuple: tuple = (1, "five", 3.4)

#all errors are of type [operator]

#and
test = five & ten #expected output: OKAY
test = five & fourfive #ERROR: Unsupported operand types for & ("int" and "float")
test = five & none #ERROR: Unsupported operand types for & ("int" and "None") 
test = stringf & stringk #ERROR: Unsupported left operand type for & ("str")
test = fourfive & twofour #ERROR: Unsupported left operand type for & ("float") 
test = intlist & strlist #ERROR: Unsupported left operand type for & ("List[Any]") 
test = five & homotuple #ERROR: Unsupported operand types for & ("int" and "Tuple[Any, ...]")  
test = none & homotuple #error: Unsupported left operand type for & ("None")
test = intlist & homotuple #error: Unsupported left operand type for & ("List[Any]")
test = five & heterotuple #error: Unsupported operand types for & ("int" and "Tuple[Any, ...]")
test = none & heterotuple #error: Unsupported left operand type for & ("None")
test = intlist & heterotuple #error: Unsupported left operand type for & ("List[Any]")

# or
test = five | ten ##expected output: OKAY
test = five | fourfive #ERROR: Unsupported operand types for | ("int" and "float")
test = five | none # ERROR: Unsupported operand types for | ("int" and "None") 
test = stringf | stringk #ERROR: Unsupported left operand type for | ("str")
test = fourfive | twofour #ERROR: Unsupported left operand type for | ("float") 
test = intlist | strlist #ERROR: Unsupported left operand type for | ("List[Any]") 
test = five | homotuple #ERROR: Unsupported operand types for | ("int" and "Tuple[Any, ...]")  
test = intlist | homotuple #error: Unsupported left operand type for | ("List[Any]")
test = none | heterotuple #error: Unsupported left operand type for | ("None")

# nor
test = not (five | ten) ##expected output: OKAY
test = not (five | fourfive) #ERROR: Unsupported operand types for | ("int" and "float")
test = not (five | none) # ERROR: Unsupported operand types for | ("int" and "None") 
test = not (stringf | stringk) #ERROR: Unsupported left operand type for | ("str")
test = not (fourfive | twofour) #ERROR: Unsupported left operand type for | ("float") 
test = not (intlist | strlist) #ERROR: Unsupported left operand type for | ("List[Any]") 
test = not (five | homotuple) #ERROR: Unsupported operand types for | ("int" and "Tuple[Any, ...]")  
test = not (intlist | homotuple) #error: Unsupported left operand type for | ("List[Any]")
test = not (none | heterotuple) #error: Unsupported left operand type for | ("None")

#xor
test = five ^ ten ##expected output: OKAY
test = five ^ fourfive #ERROR: Unsupported operand types for ^ ("int" and "float")
test = five ^ none # ERROR: Unsupported operand types for ^ ("int" and "None") 
test = stringf ^ stringk #ERROR: Unsupported left operand type for ^ ("str")
test = fourfive ^ twofour #ERROR: Unsupported left operand type for ^ ("float") 
test = intlist ^ strlist #ERROR: Unsupported left operand type for ^ ("List[Any]") 
test = five ^ homotuple #ERROR: Unsupported operand types for ^ ("int" and "Tuple[Any, ...]")  
test = intlist ^ homotuple #error: Unsupported left operand type for ^ ("List[Any]")
test = none ^ heterotuple #error: Unsupported left operand type for ^ ("None")

#not
test = not (five) ##expected output: OKAY
test = not (none) ##expected output: OKAY
test = not (intlist) ##expected output: OKAY
test = not (strlist) ##expected output: OKAY