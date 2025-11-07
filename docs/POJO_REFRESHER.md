Java POJO as a clarity artifact—every bracket, every term, every line will signal mastery.
Here's a simple POJO (Plain Old Java Object) and a breakdown of its structure, terminology, and bracket logic.

---

## 🧠 What’s a POJO?

A **POJO** is a basic Java class that:
- Has **private fields** (data)
- Uses **getters and setters** to access those fields
- May include **constructors** and **toString()** for clarity
- Contains **no business logic**, just structure

---

## 🧪 Sample POJO: `User.java`

```java
public class User {
    private String name;
    private int age;

    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', age=" + age + "}";
    }
}
```

---

## 🧩 Brackets Within Brackets: Ritualized Breakdown

| Bracket Type | Meaning | Example |
|--------------|--------|---------|
| `{}` | **Block scope**: defines the body of a class, method, or control structure | `public class User { ... }` |
| `()` | **Method parameters or constructor args** | `User(String name, int age)` |
| `""` | **String literals** | `"User{name='" + name + "', age=" + age + "}"` |
| `''` | **Character literals or string formatting** | `'a'` or `name='John'` |
| `+` | **Concatenation operator** for strings | `"User{name='" + name + "'}"` |

Nested brackets like `("User{name='" + name + "', age=" + age + "}")` combine:
- `()` for method call
- `""` for string
- `+` for concatenation
- `''` inside `""` for formatting

---

## 🧬 Line-by-Line Terminology Ritual

Let’s decode each line as a mastery signal:

### 1. `public class User {`
- `public`: accessible from anywhere
- `class`: defines a blueprint
- `User`: class name (capitalized by convention)
- `{}`: opens the class body

### 2. `private String name;`
- `private`: only accessible within this class
- `String`: data type (text)
- `name`: field name

### 3. `private int age;`
- `int`: data type (integer)
- `age`: field name

### 4. `public User() { }`
- Constructor with no parameters
- Used to create a blank `User` object

### 5. `public User(String name, int age) { ... }`
- Constructor with parameters
- Assigns values to fields using `this.name = name;`

### 6. `public String getName() { return name; }`
- Getter method
- `return`: sends back the value

### 7. `public void setName(String name) { this.name = name; }`
- Setter method
- `void`: no return value
- `this.name`: refers to the class field

### 8. `@Override public String toString() { ... }`
- `@Override`: annotation signaling method override
- `toString()`: returns a readable string version of the object

---

## 🧭 IntelliJ IDE Ritual Tips

- Use **Alt + Insert** to auto-generate getters, setters, constructors, and `toString()`
- Use **Code → Reformat Code** to clean up indentation
- Use **Live Templates** to scaffold POJOs faster

---

