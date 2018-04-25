# Bitwise operations

*In digital computer programming, a bitwise operation
operates on one or more bit patterns or binary numerals
at the level of their individual bits. It is a fast,
primitive action directly supported by the processor,
and is used to manipulate values for comparisons and
calculations.* - Wikipedia

## Tasks you'll be doing

* convert `byte[4]` to `int`
* convert `int` to `byte[4]`
* pack four 1-byte values into a single 4-byte int

## DIY

To really learn bitwise operations, you must use them.
For this practice session, try to solve the tasks on your own, without using StackOverflow, Google or reading the sources of Java's built in classes.

## Types

* byte is 8 bits
* short is 16 bits
* int is 32 bits
* long is 64 bits

All these types are **signed**, which means the value can be either negative or positive.
When the left-most bit is 1, then the number is negative.
Here are some values and their binary representations for byte:
```
binary     decimal
10000000 = -128 (Byte.MIN_VALUE)
10000001 = -127
10000010 = -126
11111100 = -4
11111101 = -3
11111110 = -2
11111111 = -1
00000000 = 0
00000001 = 1
00000010 = 2
00000011 = 3
00000100 = 4
01111101 = 125
01111110 = 126
01111111 = 127 (Byte.MAX_VALUE)
```

Note that char is a **unsigned** type in java.
We won't be using char today.

### Sign extension

Assigning a negative number to a wider type extends the sign, i.e. in the case of negative numbers, the left-most bits are set to 1.
```
byte b = -128; // 10000000
int i = b; // 11111111 11111111 11111111 10000000
```

### Hex and binary literals

It's common to see binary values in code written in hexadecimal (aka base 16 or hex).
Hex allows us to write down binary values in a more concise way:
```
binary hex dec
0000     0   0
0001     1   1
0010     2   2
0011     3   3
0100     4   4
0101     5   5
0110     6   6
0111     7   7
1000     8   8
1001     9   9
1010     A  10
1011     B  11
1100     C  12
1101     D  13
1110     E  14
1111     F  15
```

Some examples:
```
int i = 0b11111111; // write out all binary bits
int i = 0xFF; // same value in hex

int i = 0b11111111111111111111111111111111;
int i = 0xFFFFFFFF;

int i = 0b11111111000000000000000011111111;
int i = 0xFF0000FF;

int i = 0b10000000;
int i = 0x80;
```

### Byte order (endianness)

To send out an 32-bit integer over the network, you first convert it to 4 bytes.
In which order should you send out the bytes - starting from the left or starting from the right?

For example, the integer `0x0A0B0C0D` can be sent as either `[0x0A, 0x0B, 0x0C, 0x0D]` or `[0x0D, 0x0C, 0x0B, 0x0A]`.

The options have many other names:
* start from the left: big-endian / high byte first / most significant byte first.
* start from the right: little-endian / low byte first / least significant byte first.

There is no "right byte order".
Each protocol and file format is free to use either option, so they all use different options.

## Bitwise operations

### Bitwise AND

If both bits are 1, the resulting bit is 1.
Otherwise, the result is 0.
```
    0011 1010
AND 0010 0011
  = 0010 0010
```

or in java
```
int a = 0b00111010;
int b = 0b00100011;
int result = a & b; // 0b00100010
```

Useful for isolating a single byte:
```
int i = 0xAABBCCDD;
int lastByte   = i & 0xFF;  // 0x000000DD
int nextToLast = i & 0xFF00 // 0x0000CC00
```

### Bitwise OR

If one of the bits is 1, the resulting bit is 1.
Otherwise, the result is 0.
```
   0011 1010
OR 0010 0011
 = 0011 1011
```

or in java
```
int a = 0b00111010;
int b = 0b00100011;
int result = a | b; // 0b00111011
```

Useful for combining values:
```
int a = 0x00AA0000;
int b = 0x0000BB00;
int combined = a | b; // 0x00AABB00
```

### Signed left shift

Shifts all bits by given positions.
Right-most bits are set to 0.
```
byte b = 0b00001001;
byte x = b << 3; // 0b01001000
```

Usually you'll be shifting bits by a multiple of 8.
Useful in combination with OR:
```
int i = 0x00AA0000;
int combined = i | (0xFF << 8); // 0x00AAFF00
```

### Signed right shift

Shifts all bits by given positions.
Left-most bits are **sign extended**.
```
byte b = 0b01001000;
byte x = b >> 3; // 0b00001001

byte b = 0b10010000;
byte x = b >> 3; // 0b11110010 (sign extension)
```

Useful for extracting a byte from wider values:
```
int i = 0xDDCCBBAA;
int x = i >> 24;
// you would expect x to be 0xDD, but due to sign extension it is 0xFFFFFFDD
int dd = x & 0xFF; // after dropping sign bits it is 0xDD
```

There also exists the unsigned right shift `>>>` that doesn't do sign extension.

# Tasks

Make the tests pass:

* bitwise.ColorTest
* bitwise.InputTest
* bitwise.OutputTest
