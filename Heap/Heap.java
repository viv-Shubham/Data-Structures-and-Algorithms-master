package Heap;

import java.util.ArrayList;

public class Heap {

	private ArrayList<Integer> data = new ArrayList<Integer>();

	private boolean ismin;

	// Constructor-----------------------------------------------------------------------------------------------

	public Heap() {
		this.ismin = true;
	}

	public Heap(boolean ismin) {
		this.ismin = ismin;
	}

	public Heap(int[] arr, boolean ismin) {
		this.ismin = ismin;

		for (int el : arr) {

			this.data.add(el);
		}

		for (int i = this.data.size() / 2 - 1; i >= 0; i--) {
			this.downheapify(i, this.size());
		}

	}

	// General_funtions--------------------------------------------------------------------------------------

	public int size() {
		return this.data.size();
	}

	public boolean isEmpty() {
		return this.data.isEmpty();
	}

	public void display() {
		System.out.println(this.data);
	}

	public void display2() {
		display(0);
	}

	private void display(int pi) {
		while (pi < this.size()) {
			String str = "";

			int lci = 2 * pi + 1;
			int rci = 2 * pi + 2;

			if (lci < this.size()) {
				str += this.data.get(lci);
			} else {
				str += ".";
			}

			str += "=>" + this.data.get(pi) + "<=";

			if (rci < this.size()) {
				str += this.data.get(rci);
			} else {
				str += ".";
			}
			System.out.println(str);
			pi++;
		}
		System.out.println();
	}

	public void clear() {
		this.data.clear();
	}
	// add---------------------------------------------------------------------------------------------------

	public void add(int data) {
		this.data.add(data);

		int ci = this.data.size() - 1;
		upheapify(ci);
	}

	// Remove,poll_and_peek-----------------------------------------------------------------------------------

	public int remove() throws Exception {
		if (this.size() == 0) {
			throw new Exception("NoSuchElementException");
		}
		swap(0, this.size() - 1);
		int rv = this.data.remove(this.size() - 1);
		downheapify(0, this.size());
		return rv;
	}

	public int poll() throws Exception {
		if (this.size() == 0) {
			throw new Exception("NullPointerException");
		}
		swap(0, this.size() - 1);
		int rv = this.data.remove(this.size() - 1);
		downheapify(0, this.size());
		return rv;
	}

	public int peek() throws Exception {
		if (this.size() == 0) {
			throw new Exception("NoSuchElementException");
		}
		return this.data.get(0);
	}

	public void update(int data) {
		int idx = -1;
		for (int i = 0; i < this.data.size(); i++) {
			if (data == this.data.get(i)) {
				idx = i;
				break;
			}
		}

		upheapify(idx);
		downheapify(idx, this.size());
	}

	// upheapify_and_Downheapify------------------------------------------------------------------------------

	private void upheapify(int ci) { // Child_Index_and_Parent_Index.
		if (ci == 0) {
			return;
		}

		int pi = (ci - 1) / 2;
		if (higherPriority(ci, pi)) {
			swap(pi, ci);
			upheapify(pi);
		}

	}

	private void downheapify(int pi, int n) {

		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;

		int maxi = pi; // MaxIndex_or_HighPriorityIndex.

		if (lci < n && this.higherPriority(lci, maxi)) {
			maxi = lci;
		}

		if (rci < n && this.higherPriority(rci, maxi)) {
			maxi = rci;
		}

		if (pi != maxi) {
			swap(pi, maxi);
			downheapify(maxi, n);

		}

	}

	// swap_and_higher_priority--------------------------------------------------------------------------------

	private void swap(int i, int j) {
		int idata = this.data.get(i);
		int jdata = this.data.get(j);

		this.data.set(j, idata);
		this.data.set(i, jdata);

	}

	private boolean higherPriority(int i, int j) {
		int idata = this.data.get(i);
		int jdata = this.data.get(j);

		if (ismin) {
			return idata < jdata ? true : false;
		} else {
			return idata > jdata ? true : false;
		}
	}

	// Contains------------------------------------------------------------------------------------------------------

	public boolean contains(int data) {
		for (int i : this.data) {
			if (this.data.get(i) == data) {
				return true;
			}
		}

		return false;
	}

	// HeapSort

	public void HeapSort() {
		for (int i = this.size() - 1; i > 0; i--) {
			swap(0, i);
			downheapify(0, i);
		}

	}

}
