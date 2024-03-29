[返回主目录](../README.md)

在制作自己的脚本语言之前，需要明确一些概念。

## 机器语言

机器语言可以直接被加载到内存中执行。

机器语言是一串01组成的，难以被人阅读和编辑。

## 汇编语言

- 汇编语言是对机器语言的简单封装

- 汇编语言需要先通过*汇编器* 翻译为机器语言才可以被执行

## 解释器

- 解释器是一种**执行**程序的软件

- 解释器直接按照程序中的算法逻辑执行任务

- 如果被执行的程序是虚拟机的机器语言或者类似于机器语言的程序语言写成，这种软件也称为*虚拟机*

## 编译器

- 编译器是一种**语言转换**的软件

- 编译器通常将原程序转换为机器语言

举个几个例子来说明：

Java 语言首先通过编译器把源代码转换为Java虚拟机语言。运行阶段，Java*虚拟机*的解释器将负责执行Java虚拟机语言。

JavaScript 不需要编译，可以直接被浏览器的解释器执行。

目前为了性能优化，解释器会在执行过程中，将一部分代码通过编译器转换为机器码来使用。例如Java虚拟的**JIT**编译。

