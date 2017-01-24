# yoar - Yet anOther Android Redux

This is someone else making another android redux. OMG please stop!

But wait! The implementation supports **threads** ! Yes, that's right! **Threads**!

Read on to learn more.

## Implementation

You can find the redux code [here](lib/src/main/java/com/github/kyleilantzis/yoar)

## Store

To create a store use **Store.createStore**

```java
public class MyState {
    ...
}

public class MyReducer implements Reducer<MyState> {
    MyState reduce( Action action, MyState myState ) {
        ...
    }
}

Store<MyState> store = Store.createStore( new MyState(), new MyReducer(), AndroidExecutorService.newExecutorService() );
```

**AndroidExecutorService.newExecutorService()** is responsible for dispatching work on threads. It returns a
[Store.ExecutorService](lib/src/main/java/com/github/kyleilantzis/yoar/redux/Store.java#L87).

The work is scheduled on the UiThread (from here on called the default tread) by obtaining an executor with type
**Store.RUNNABLE_ACTION_TYPE_DEFAULT**

## Subscribe to a store

Subscribe to a store with **Store.subscribe** passing in a
[Store.StateChangeListener](lib/src/main/java/com/github/kyleilantzis/yoar/redux/Store.java#L83)

**Guarantee**: onChange will be called on the default thread.

If you implemented your own Store.ExecutorService then it will execute with whatever executor is returned
for the type **Store.RUNNABLE_ACTION_TYPE_DEFAULT**

```java
StateChangeListener<MyState> listener = new StateChangeListener<MyState> {
    public void onChange( MyState state ) {
        ...
    }
}

Store<MyState> store = Store.createStore(...);

store.subscribe( listener );

...

store.unsubscribe( listener );
```

## Dispatch an action

**Guarantee**: dispatch will call your reducer on the default thread.

If you implemented your own Store.ExecutorService then it will execute with whatever executor is returned
for the type **Store.RUNNABLE_ACTION_TYPE_DEFAULT**

```java
Store<MyState> store = Store.createStore(...);

store.dispatch( Action.create("my-action") );

store.dispatch( Action.create("my-other-action", "my-value") );
```

## Dispatch and threads

So you read up to here and are interested in how threads are supported? Fantastic!

With a store you can dispatch a
[RunnableAction](lib/src/main/java/com/github/kyleilantzis/yoar/redux/RunnableAction.java)

A RunnableAction is like a Runnable but you specify the thread you want it to run on. You also get access
to the store from the run method.

```java
Store<MyState> store = Store.createStore(...);

store.dispatch( new RunnableAction<MyStore<MyState>> {

    public String getType() { return "background"; }

    public void run(Store<MyState> store) {

        store.dispatch( Action.create("my-background-job-start") );

        ...

        store.dispatch( Action.create("my-background-job-progress", percentDone ) );

        ...

        store.dispatch( Action.create("my-background-job-done", result ) );
    }

} );
```

**getType()** returns "background", this tells the store what thread to run the action on. In this case
a thread named "background".

You can call store.dispatch( ... ) passing an Action and the store will be sure to reduce the action on the
default thread.

### Dispatch inception

You can even dispatch more runnable actions within a runnable action.

```java
store.dispatch( new RunnableAction<MyStore<MyState>> {

    public String getType() { return "master"; }

    public void run(Store<MyState> store) {

        store.dispatch( new RunnableAction<MyStore<MyState>> {

            public String getType() { return "slave-1"; }

            public void run(Store<MyState> store) {
                ...
            }

        } );

        store.dispatch( new RunnableAction<MyStore<MyState>> {

            public String getType() { return "slave-2"; }

            public void run(Store<MyState> store) {
                ...
            }

        } );
    }

} );
```

### Dispatch default thread

If your runnable action returns type **Store.RUNNABLE_ACTION_TYPE_DEFAULT** then it will
run on the default thread. With the android Store.ExecutorService this is equivalent to posting a
runnable on the UiThread.

```java
store.dispatch( new RunnableAction<MyStore<MyState>> {

    public String getType() { return Store.RUNNABLE_ACTION_TYPE_DEFAULT; }

    public void run(Store<MyState> store) {
        ...
    }

} );
```

## Thread safety

Your reducer must be a pure function and your state must be immutable.
This will ensure thread safety in your runnable actions.

There is no guarantee about what the state will be as your runnable action executes.

```java
store.dispatch( new RunnableAction<MyStore<MyState>> {

    public String getType() { return "background"; }

    public void run(Store<MyState> store) {

        MyState state1 = store.getState();

        ...

        MyState state2 = store.getState();
    }

} );
```

In the above example maybe state1 == state2 or state1 != state2.
It could be that the state has changed on the UiThread while the runnable action is executing.

In most cases obtaining the state at the start of the run method will be sufficient.
Otherwise your must program with this thread safety warning in mind.

## Example

You can find an example application
[here](example/src/main/java/com/github/kyleilantzis/yoar/example)

## LICENSE

MIT
