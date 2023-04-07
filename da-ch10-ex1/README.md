# 10.1.1 Getting a thread dump using a profiler

https://visualvm.github.io/download.html

What do we do when we have a frozen app and we want to identify the problem’s root cause? Using a profiler to analyze the locks most likely won’t work in a scenario in which the app, or part of it, is frozen. Instead of analyzing the locks during execution, as we did in chapter 9, we’ll take a snapshot just of the app’s thread states. We’ll read this snapshot (i.e., thread dump) and find out which threads are affecting each other and causing the app to freeze.

You can obtain a thread dump either by using a profiler tool (e.g., VisualVM, JProfiler) or by directly calling a tool provided by the JDK using the command line. In this section, we’ll discuss how to obtain a thread dump using a profiler, and in section 10.1.2, we’ll learn how to get the same information using the command line.

We’ll start our application (project da-ch10-ex1) and wait a few seconds for it to enter a deadlock. You’ll know the app gets into a deadlock when it no longer writes messages in the console (it gets stuck).


To start the project, follow these steps:

* Start project da-ch10-ex1.
    ```
    mvn clean package
    java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heapdump.bin  -cp .\target\da-ch10-ex1-1.0-SNAPSHOT.jar  main.Main
    ```
* Start VisualVM.
    ```
    D:\userData\robert_desktop\visualvm_215\bin>visualvm  --jdkhome  "D:\DATA\JAVA\old_jdks\jdk8u362-b09"
    ```
* Select a process for project da-ch10-ex1 in VisualVM.
* Go to the Threads tab in VisualVM.

Getting the thread dump using a profiler is a simple approach. It’s no more than the click of a button. Let’s use VisualVM to get a thread dump. Figure 10.2 shows the Visual VM interface. You can see that VisualVM is smart and figured out that some of the threads of our process ran into a deadlock. This is indicated in the Threads tab.


###### Figure 10.2 When some of the app’s threads get into a deadlock, VisualVM indicates the situation with a message in the Threads tab. Notice that both the ``_Consumer`` and ``_Producer`` threads are locked on the graphic timeline. To get a thread dump, you simply select the Thread Dump button in the window’s upper-right corner.
![Figure 10.2 When some of the app’s threads get into a deadlock.](./material/CH10_F02_Spilca3.png) 

After the thread dump is collected, the interface looks like figure 10.3. The thread dump is represented as plain text that describes the app threads and provides details about them (e.g., their state in the life cycle, who blocks them, etc.).

**At first, you might not understand the thread dump text in figure 10.3. Later in this chapter, you’ll learn to read it.**


###### Figure 10.3 A thread dump in plain text that describes an app’s threads. In the thread dump we collected, we can find the two deadlocked threads ``_Consumer`` and ``_Producer``.
![Figure 10.3 A thread dump in plain text that describes an app’s threads.](./material/CH10_F03_Spilca3.png) 

# 10.1.1 Getting a thread dump using a profiler

10.1.2 Generating a thread dump from the command line
A thread dump can also be obtained using the command line. This approach is particularly useful when you need to get a thread dump from a remote environment. Most of the time, you won’t be able to remote profile an app installed in an environment (and remember, remote profiling and remote debugging aren’t recommended in a production environment, as discussed in chapter 4). Since in most cases you can only access a remote environment using the command line, you need to know how to get a thread dump this way too.

Fortunately, getting a thread dump using the command line is quite easy :

* Find the process ID for which you want a thread dump.
  ```
  jps -l
  ```
* Get the thread dump as text data (raw data) and save it in a file.
  ```
    jstack <<PID>>    
    jstack <<PID>>   > PATH\stack_trace.tdump
  ```
* Load the saved thread dump to make it easier to read in a profiler tool.
  * For example, to open it in VisualVM, you select File > Load.
  * We’ll use a tool named fastThread (https://fastthread.io/), which provides a simpler way to visualize the data in a thread dump.