package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T> {

   private static final int INDEX_INVALID = -1;

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method) {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element) {

      if (super.isFull()) {
         throw new HashtableOverflowException();
      }

      if (element != null && search(element) == null) {

         int probe = 0;
         int hashIndex = getHashIndex(element, probe);

         while (probe < super.capacity()) {
            if (table[hashIndex] == null || deletedElement.equals(table[hashIndex])) {
               table[hashIndex] = element;
               super.elements++;
               return;
            } else {
               probe++;
               hashIndex = getHashIndex(element, probe);
               super.COLLISIONS++;
            }
         }
      }
   }

   @Override
   public void remove(T element) {

      if (element == null) {
         return;
      }

      if (this.indexOf(element) != -1) {
         int hashIndex = indexOf(element);
         table[hashIndex] = super.deletedElement;
         super.elements--;
      }
   }

   @Override
   public T search(T element) {
      if (element != null) {

         int probe = 0;
         int hashIndex = getHashIndex(element, probe);

         while (table[hashIndex] != null && probe < super.capacity() && !deletedElement.equals(table[hashIndex])) {
            if (table[hashIndex].equals(element)) {
               return element;
            } else {
               probe++;
               hashIndex = getHashIndex(element, probe);
            }
         }
      }
      return null;
   }

   @Override
   public int indexOf(T element) {
      if (element != null) {

         int probe = 0;
         int hashIndex = getHashIndex(element, probe);

         while (table[hashIndex] != null && probe < super.capacity() && !deletedElement.equals(table[hashIndex])) {
            if (table[hashIndex].equals(element)) {
               return hashIndex;
            } else {
               probe++;
               hashIndex = getHashIndex(element, probe);
            }
         }
      }
      return INDEX_INVALID;

   }

   private int getHashIndex(T element, int probe) {

      int hash = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
      return hash;
   }
}
