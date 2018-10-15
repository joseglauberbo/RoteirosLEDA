package adt.hashtable.closed;

import java.util.Iterator;
import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionDivisionMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import adt.hashtable.hashfunction.HashFunctionMultiplicationMethod;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

   private static final int INDEX_INVALID = -1;

   /**
    * A hash table with closed address works with a hash function with closed
    * address. Such a function can follow one of these methods: DIVISION or
    * MULTIPLICATION. In the DIVISION method, it is useful to change the size
    * of the table to an integer that is prime. This can be achieved by
    * producing such a prime number that is bigger and close to the desired
    * size.
    * 
    * For doing that, you have auxiliary methods: Util.isPrime and
    * getPrimeAbove as documented bellow.
    * 
    * The length of the internal table must be the immediate prime number
    * greater than the given size. For example, if size=10 then the length must
    * be 11. If size=20, the length must be 23. You must implement this idea in
    * the auxiliary method getPrimeAbove(int size) and use it.
    * 
    * @param desiredSize
    * @param method
    */

   @SuppressWarnings({ "rawtypes", "unchecked" })
   public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
      int realSize = desiredSize;

      if (method == HashFunctionClosedAddressMethod.DIVISION) {
         realSize = this.getPrimeAbove(desiredSize); // real size must the
         // the immediate prime
         // above
      }
      initiateInternalTable(realSize);
      HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
      this.hashFunction = function;
   }

   // AUXILIARY
   /**
    * It returns the prime number that is closest (and greater) to the given
    * number. You can use the method Util.isPrime to check if a number is
    * prime.
    */
   int getPrimeAbove(int number) {

      if (util.Util.isPrime(number)) {
         return number;
      } else {
         while (!util.Util.isPrime(number)) {
            number++;
         }
      }
      return number;
   }

   @Override
   public void insert(T element) {

      if (element != null || !isFull()) {

         int hash = this.getHash(element);

         if (this.table[hash] == null) {
            this.table[hash] = new LinkedList<T>();
         }

         //verificando se o hash ja esta ocupado com algum elemento, se for eh do tipo
         //linkedList;
         if (this.table[hash] instanceof LinkedList) {
            LinkedList<T> myLinked = (LinkedList<T>) this.table[hash];

            if (!myLinked.isEmpty()) {
               this.COLLISIONS++;
            }

            myLinked.add(element);
            this.elements++;
         }
      }
   }

   @Override
   public void remove(T element) {

      if (element != null || !this.isEmpty()) {

         int hash = this.getHash(element);

         //verificando se o hash ja esta ocupado com algum elemento, se for eh do tipo
         //linkedList;
         if (this.table[hash] instanceof LinkedList) {

            LinkedList<T> myLinked = (LinkedList<T>) this.table[hash];

            if (myLinked.size() > 1) {
               this.COLLISIONS--;
            }

            myLinked.removeLastOccurrence(element);
            this.elements--;
         }
      }
   }

   @Override
   public T search(T element) {
      if (element != null) {

         int hash = this.getHash(element);

         if (this.table[hash] instanceof LinkedList) {

            LinkedList<T> myLinked = (LinkedList<T>) this.table[hash];
            
            if (myLinked.contains(element)) {
               return element;
            }
         }
      }
      return null;

   }

   @Override
   public int indexOf(T element) {

      if (element != null) {

         int hash = this.getHash(element);

         if (this.table[hash] instanceof LinkedList) {
            LinkedList<T> myLinked = (LinkedList<T>) this.table[hash];

            if (myLinked.contains(element)) {
               return hash;
            }
         }
      }
      return INDEX_INVALID;
   }

   private int getHash(T element) {

      if (this.getHashFunction() instanceof HashFunctionDivisionMethod) {
         int hash = ((HashFunctionDivisionMethod<T>) this.getHashFunction()).hash(element);
         return hash;
      } else {
         int hash = ((HashFunctionMultiplicationMethod<T>) this.getHashFunction()).hash(element);
         return hash;
      }
   }
}
