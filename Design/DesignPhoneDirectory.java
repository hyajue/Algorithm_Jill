package OOD;

import java.util.Queue;
import java.util.Set;
import java.util.BitSet;

/**
Design a Phone Directory which supports the following operations:

get: Provide a number which is not assigned to anyone.
check: Check if a number is available or not.
release: Recycle or release a number.

Example:
// Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
PhoneDirectory directory = new PhoneDirectory(3);

// It can return any available phone number. Here we assume it returns 0.
directory.get();

// Assume it returns 1.
directory.get();

// The number 2 is available, so return true.
directory.check(2);

// It returns 2, the only number that is left.
directory.get();

// The number 2 is no longer available, so return false.
directory.check(2);

// Release number 2 back to the pool.
directory.release(2);

// Number 2 is available again, return true.
directory.check(2);
 */
/*
思路：
用queue来存还没被使用的号码，用set来存被占用的号码
 */
public class PhoneDirectory {
	Set<Integer> used = new HashSet<Integer>();
	Queue<Integer> available = new LinkedList<Integer>();
	int max = 0;
	public PhoneDirectory(int maxNumbers) {
		max = maxNumbers;
		for(int i : max)
			available.offer(i);
	}
	public int get() {
		Integer ret  = available.poll();
		if(ret == null)
			return -1;
		used.add(ret);
		return ret;
	}
	public boolean check(int number) {
		if(number >= max || number < 0)
			return false;
		return !used.contains(number);
	}
	public void release(int number) {
		if(used.remove(number))
			available.offer(number);
	}
}

//@ BitSet
/*
Java.util.BitSet可以按位存储。
计算机中一个字节（byte）占8位（bit），我们java中数据至少按字节存储的，
比如一个int占4个字节。
如果遇到大的数据量，这样必然会需要很大存储空间和内存。
如何减少数据占用存储空间和内存可以用算法解决。
java.util.BitSet就提供了这样的算法。
比如有一堆数字，需要存储，source=[3,5,6,9]
用int就需要4*4个字节。
java.util.BitSet可以存true/false。
如果用java.util.BitSet，则会少很多，其原理是：
1，先找出数据中最大值maxvalue=9
2，声明一个BitSet bs,它的size是maxvalue+1=10
3，遍历数据source，bs[source[i]]设置成true.
最后的值是：
(0为false;1为true)
bs [0,0,0,1,0,1,1,0,0,1]
                3,   5,6,       9
这样一个本来要int型需要占4字节共32位的数字现在只用了1位！
比例32:1  
这样就省下了很大空间。
 */
public class PhoneDirectory extends BitSet{
	BitSet bitset;
	int max; //max limit allowed
	int smallestFreeIndex; //current smallest index of the free bit
	public PhoneDirectory(int maxNumbers) {
		this.bitset = new BitSet(maxNumbers);
		this.max = maxNumbers;
	}
	public int get() {
		if(smallestFreeIndex == max)
			return -1;
		int num = smallestFreeIndex;
		bitset.set(smallestFreeIndex);
		smallestFreeIndex = bitset.nextClearBit(smallestFreeIndex);
		return num;
	}
	public boolean check(int number) {
		return bitset.get(number) == false;
	}
	public void release(int number) {
		if(bitset.get(number) == false)
			return;
		bitset.clear(number);
		if(number < smallestFreeIndex)
			smallestFreeIndex = number;
	}
}
