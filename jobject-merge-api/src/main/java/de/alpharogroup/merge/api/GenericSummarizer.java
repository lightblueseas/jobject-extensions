package de.alpharogroup.merge.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * The class {@link GenericSummarizer} can merge generic items of the given generic type
 *
 * @param <T>
 *            the generic type of the objects
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public abstract class GenericSummarizer<T extends Mergeable<T>> implements Summarizer<T>
{

	/** The Constant value for the default max iteration. */
	public static final int DEFAULT_MAX_ITERATION = 9999;

	/** The max iteration which the iteration will break in the while loop. */
	int maxIteration;

	/**
	 * Instantiates a new {@link GenericSummarizer} with the default max iteration
	 */
	public GenericSummarizer()
	{
		this(DEFAULT_MAX_ITERATION);
	}

	/**
	 * Instantiates a new {@link GenericSummarizer} with the given max iteration
	 *
	 * @param maxIteration
	 *            the max iteration
	 */
	public GenericSummarizer(int maxIteration)
	{
		this.maxIteration = maxIteration;
	}

	/**
	 * Removes duplicate entries and returns a new {@link List}
	 *
	 * @param mergeItems
	 *            the merge items
	 * @param toRemove
	 *            the to remove
	 * @return the list
	 */
	private List<T> clean(final List<T> mergeItems, final List<T> toRemove)
	{
		final Set<T> set = new HashSet<>(mergeItems);
		set.removeAll(toRemove);
		return new ArrayList<>(set);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> merge(final List<T> items)
	{
		final List<T> toAdd = new ArrayList<>();
		final List<T> toRemove = new ArrayList<>();
		sort(items);
		List<T> mergedItems = new ArrayList<>(items);
		List<T> lastIterated = new ArrayList<>(mergedItems);
		int initialSize = mergedItems.size();
		int count = 0;
		while (count < maxIteration)
		{
			merge(mergedItems, toAdd, toRemove);
			mergedItems = clean(toAdd, toRemove);
			sort(mergedItems);
			toAdd.clear();
			toRemove.clear();
			final int newSize = mergedItems.size();
            if (initialSize == newSize && mergedItems.equals(lastIterated)) {
                break;
            }
			initialSize = newSize;
			lastIterated = new ArrayList<>(mergedItems);
			count++;
		}
		return mergedItems;
	}

	/**
	 * Iterates over the given list sourceItems and merge items that can be merged. The result of
	 * the merged items are saved in the given list mergeTs. All items that have to be removed are
	 * saved in the given list toRemove.
	 *
	 * @param sourceItems
	 *            the source items
	 * @param mergedItems
	 *            All merged items are saved in this list
	 * @param toRemove
	 *            All items that have to be removed are saved in this list
	 */
    private void merge(final List<T> sourceItems, final List<T> mergedItems, final List<T> toRemove) {
        for (final T clonedItem : sourceItems) {
            process(sourceItems, mergedItems, toRemove, clonedItem);
        }
    }

    private void process(List<T> sourceItems, List<T> mergedItems, List<T> toRemove, T clonedItem) {
        for (final T sourceItem : sourceItems) {
            if (clonedItem.equals(sourceItem)) {
                if (!mergedItems.contains(sourceItem)) {
                    mergedItems.add(sourceItem);
                }
                continue;
            }
            filterMergedItems(mergedItems, toRemove, clonedItem, sourceItem);
        }
    }

    private void filterMergedItems(List<T> mergedItems, List<T> toRemove, T clonedItem, T sourceItem) {
        final T mergedItem = merge(clonedItem, sourceItem);
        if (!clonedItem.equals(mergedItem)) {
            if (!mergedItems.contains(mergedItem)) {
                mergedItems.add(mergedItem);
            }
            if (!toRemove.contains(clonedItem) && !mergedItem.equals(clonedItem)) {
                toRemove.add(clonedItem);
            }
            if (!toRemove.contains(sourceItem) && !mergedItem.equals(sourceItem)) {
                toRemove.add(sourceItem);
            }
        } else {
            if (!mergedItems.contains(sourceItem)) {
                mergedItems.add(sourceItem);
            }
        }
    }

	/**
	 * Sorts the given {@link List} with the specific {@link Comparator} that will be defined in the
	 * factory method
	 *
	 * @param list
	 *            the list
	 */
	protected void sort(List<T> list)
	{
		Collections.sort(list, newComparator());
	}

	/**
	 * Abstract factory method that have to be implemented from classes that extends this class
	 *
	 * @return the comparator
	 */
	protected abstract Comparator<T> newComparator();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T merge(final T object, final T other)
	{
		return (T)object.merge(other);
	}

}
