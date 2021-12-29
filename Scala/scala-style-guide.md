### 1 Avoid Casts and Type Tests
Never use isInstanceOf or asInstanceOf - there’s always a better solution, both for the assignments, and also for any real-world Scala project. If you find yourself wanting to use casts, take a step back and think about what you’re trying to achieve. Re-read the assignment instructions and have another look at the corresponding lecture videos.

### 2 Indentation
Make sure your code is properly indented, it becomes a lot more readable.

This might seem trivial and not very relevant for our exercises, but imagine yourself in the future being part of a team, working on the same files with other coders: it is very important that everybody respects the style rules to keep the code healthy.

If your editor does not do indentation the way you would like it to have, you should find out how to change its settings. In Scala, the standard is to indent using 2 spaces (no tabs).

### 3 Line Length and Whitespace
Make sure the lines are not too long, otherwise your code is very hard to read. Instead of writing very long lines, introduce some local value bindings. Using whitespace uniformly makes your code more readable.

Example: 

```scala
if(p(this.head))this.tail.filter0(p, accu.incl(this.head))else this.tail.filter0(p, accu)
```

Better
```scala
if p(this.head) then
  this.tail.filter0(p, accu.incl(this.head))
else
  this.tail.filter0(p, accu)
```

Even better
```scala
val newAccu =
  if p(this.head) then accu.incl(this.head)
  else accu
this.tail.filter0(p, newAccu)
```

### 4 Use local Values to simplify complex Expressions
When writing code in functional style, methods are often implemented as a combination of function calls. If such a combined expression grows too big, the code might become hard to understand.

In such cases it is better to store some arguments in a local value before passing them to the function (see #3 above). Make sure that the local value has a meaningful name (see #5 below)!

### 5 Choose meaningful Names for Methods and Values
The names of methods, fields and values should be carefully chosen so that the source code is easy to understand. A method name should make it clear what the method does. No, temp is never a good name.

A few improvable examples
```scala
val temp = sortFuntion0(list.head, tweet)   // what does sortFunction0 do?
def temp(first: TweetSet, second : TweetSet): TweetSet = ...
def un(th: TweetSet,acc: TweetSet): TweetSet = ...
val c = if p(elem) accu.incl(elem) else accu
def loop(accu: Trending, current: TweetSet): Trending = ...
def help(ch: Char, L2: List[Char], compteur: Int): (Char, Int) = ...
def help2(L: List[(Char, Int)], L2: List[Char]): List[(Char, Int)] = ...
```

### 6 Common Subexpressions
You should avoid unnecessary invocations of computation-intensive methods. 
For example
```scala
this.remove(this.findMin).ascending(t + this.findMin)
```

invokes the `this.findMin` method twice. If each invocation is expensive (e.g. has to traverse an entire data structure) and does not have a side-effect, you can save one by introducing a local value binding:

```scala
val min = this.findMin
this.remove(min).ascending(t + min)
```
This becomes even more important if the function is invoked recursively: in this case the method is not only invoked multiple times, but an exponential number of times.

### 7 Avoid using Return
In Scala, you often don’t need to use explicit returns because control structures such as if are expressions.

### 8 Avoid mutable local Variables
Since this is a course on functional programming, we want you to get used to writing code in a purely functional style, without using side-effecting operations. You can often rewrite code that uses mutable local variables to code with helper functions that take accumulators. 

### 9 Eliminate redundant “If” Expressions
Instead of
```scala
if cond then true else false
```
you can simply write
```scala
cond
```


_Source: cousera - Programming Reactive Systems
