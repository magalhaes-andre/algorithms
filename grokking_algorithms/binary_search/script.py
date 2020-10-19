# Search problem: I want to search for a person whose name starts with K in a phone book but don't want to start at A and go through each name on the list. I'd rather preffer to look somewhere in the middle already and look through less names.
# Solution: Binary Search
# Needs to receive a sorted list of elements, if the element we need is found, we return its position, otherwise null would come in handy.
# In a case where i'm searching through a list of 240.000 names, going one by one may end needing to do 240.000 comparisons before finding out my number, while with binary search i could cut this down to 18 searches.

names = ["Anderson", "Carlson", "Marcus", "Bridgett" "Carlyle", "Karen"]
letters = ["A", "C", "M", "B", "K"]
numbers = [9, 5, 3, 7, 1, 2]
def binary_search(list, item):
    list.sort()

    low = 0
    high = len(list)-1

    while low <= high:
        mid = (low + high)
        guess = list[mid]
        if guess == item:
            return mid
        if guess > item:
            high = mid - 1
        else:
            low = mid -1
    return None

print (binary_search(numbers, 3)) # Should return 2
print (binary_search(letters, "K")) # Should return 3

# Question Time!
# 1. Suppose you have a sorted list of 128 names, and you're searching through it using binary search. What's the maximum number of steps it would take?
# 2. Suppose you double the size of the list, What's the maximum number of steps now? 