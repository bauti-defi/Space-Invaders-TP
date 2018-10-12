package com.austral.spaceinvaders.util;

import java.util.ArrayList;
import java.util.List;

public class DistributionList<E extends Distributable> {

	private final List<E> elements = new ArrayList<>();
	private int distributionWidth;

	public DistributionList(E... elements) {
		for (E element : elements) {
			this.elements.add(element);
		}
		calculateDistributionWidth();
	}

	private final void calculateDistributionWidth() {
		//Find the width of the distribution
		distributionWidth = elements.stream().mapToInt(Distributable::getPercentProbability).sum();
	}

	public void add(E element) {
		this.elements.add(element);
		calculateDistributionWidth();
	}


	public void remove(E element) {
		this.elements.remove(element);
		calculateDistributionWidth();
	}

	//Gets random element according to distribution
	public E get() {
		if (!elements.isEmpty()) {
			int randomDistributionPoint = RandomGenerator.getRandom(distributionWidth);

			for (E element : elements) {
				if ((randomDistributionPoint -= element.getPercentProbability()) <= 0) {
					return element;
				}
			}
		}
		throw new NullPointerException();
	}
}
