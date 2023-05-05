class Person:
    def __init__(self, name: str, surname: str, age: int):
        self.name = name
        self.surname = surname
        self.age = age

    def __str__(self):
        return f"{self.surname} {self.name}({self.age})"
    
    def oneYearLater(self):
        self.age += 1
        return self.age
    
    def concatNameSurname(self):
        return self.surname+self.name
    
    def isSpaceAge(self):
        return self.age.isspace()
    
class Car:
    def __init__(self, year:int, model:str, speed:int = 0):
        self.__year_model = year
        self.__model = model
        self.__speed = speed

    def set_year_model(self, year:int):
        self.__year_model = year

    def set_model(self, model:str):
        self.__model = model

    def set_speed(self, speed:int = 0):
        self.__speed = speed

    def get_year_model(self):
        return self.__year_model

    def get_model(self):
        return self.__model

    def get_speed(self):
        return self.__speed

    #methods
    def accelerate(self):
        self.__speed +=5

    def brake(self):
        self.__speed -=5

    def get_speed(self):
        return self.__speed
    
class MyClass:
    def __init__(self, my_dict: dict, my_list: list, my_tuple: tuple):
        self.my_dict = my_dict
        self.my_list = my_list
        self.my_tuple = my_tuple

    def changeDictToList(self):
        for key, value in self.my_dict.items():
            self.my_tuple = key
            self.my_list = value

test1 = Person("Jane", 14, 40) #error: Argument 2 to "Person" has incompatible type "int"; expected "str"  [arg-type]
test2 = Person("Jane", "Smith", "20") #error: Argument 3 to "Person" has incompatible type "str"; expected "int"  [arg-type]

test3 = Car(20.2, 1992) #58: error: Argument 1 to "Car" has incompatible type "float"; expected "int"  [arg-type]
#58:error: Argument 2 to "Car" has incompatible type "int"; expected "str"  [arg-type]

test4 = MyClass({"apple": 2, "banana": 4, "orange": 1}, [1, 2], ("a", "f")) #Okay
test4.changeDictToList() #Okay
test4.my_dict = test4.my_list #ERROR: Incompatible types in assignment (expression has type "List[Any]", variable has type "Dict[Any, Any]") [assignment]

test5 = MyClass([1,2], [1, 2], (2,3)) #ERROR: Argument 1 to "MyClass" has incompatible type "List[int]"; expected "Dict[Any, Any]"  [arg-type]