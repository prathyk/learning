##Initial Thoughts
* Refactoring requires careful consideration and patient operation. One should not be aggressive. Some important principles are:
	* Do one thing at a time. When you are refactoring, just do refactoring but dont jump in to doing other things like fixing bugs. Keep note of those side things in a todolist. Make sure when refactoring, you never change the functionality even if there is a bug. 
	* Smell the code, take one target and work towards it. Dont just try to make the code beautiful. Take one task or one aspect and work in small steps towards that. 
	* Follow the rhythm - test, small refactoring, test, small refactoring and so on....
	* Stop refactoring when you are not clear where you are going. If the current state is already better than previous, then check-in. Otherwise, throw away what you did.
	* Real art in refactoring is to know when to stop.
* Refactoring should be the first task before we work on a new feature or bug. That task should be independent and should not change the functionality at all. So every epic or story, one task can be about what refactoring should we take up. Of course, we need to make sure we have sufficient tests. 


* Examples should be the way to demonstrate anything. Just like BG will not be fully clear until we have SB also.
* Communication is the main thing in programming. Your code is good when others are able to understand what it does.
* Before adding a feature, take refactoring effort to change the code without changing the functionality. Then add the new feature.
* Tests should be the first thing and those tests should be fast enough that you don't feel lazy to always keep them running for every small change.:w

