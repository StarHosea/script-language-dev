[返回主目录](../README.md)

我们设计的语言取名为 Bird，它非常简单，支持如下特性

- 单行注释
- 加，减，乘，除运算
- if 条件语句
- if-else 条件语句
- while 语句

## 基本数据类型

Bird 的基本数据类型有：整型数字，字符串

## 运算符

Bird 支持四则运算

```
a = 1 + 1 ;
b = 1 - 1 ;
c = 1 * 1 ;
d = 1 / 1 ;
```
## 变量声明

Bird 变量不需要显示指定类型，根据变量赋值自动推断出类型

下面是正确的声明语句

```js
num = 123;
str = "123";
```

对应Java的声明如下

```java
int a = 123;
String a = "123";
```

## 条件语句

Bird 支持 if， if-else 语句。

书写要求与 Java ， Javascript 相似，但是条件语句不需要加括号。

- if 语句

```javascript
if  a < b {
    
}
```

- if - else 语句

```javascript
if a < b {
    
} else {
    
}
```

## 循环语句

Bird 支持 while 循环。

书写要求与 Java ，JavaScript 相似，但是条件语句不需要加括号。

```javascript
while a < b {

}
```
## 句尾的分号

Bird 像Javascript一样，一行代码有多条语句时，以分号分隔，行尾的分号可以省略

下面三种写法都是正确的

- 行尾加分号

```javascript
a = 1 ;
b = 1 ;
```

- 一行多条语句

```javascript
a = 1 ; b = 1 ;
```

- 行尾分号省略

```javascript
a = 1
b = 1
```

为了实现简单，我们可以想象Bird为每一行代码末尾都自动添加了分号。

那么，如下的条件语句书写就是错误的

```javascript
if a < b 
{
    
}
```

因为该语句自动被变为

```javascript
if a < b ;
{ 
    
}
```

