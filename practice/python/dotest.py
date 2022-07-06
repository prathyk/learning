def average(values):
  """ Computes the mean of list of numbers.

  >>> print(average([20, 30, 70]))
  43.0
  """
  return sum(values) / len(values)

import doctest
doctest.testmod()
