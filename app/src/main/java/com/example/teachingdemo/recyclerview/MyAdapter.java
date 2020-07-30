package com.example.teachingdemo.recyclerview;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.LayoutInflaterCompat;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teachingdemo.R;
import com.example.teachingdemo.UserBean;

import java.security.PrivilegedAction;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author sjc
 * @Date 2020/6/6.
 * GitHub：
 * Email：jiachen.sun@credit.com
 * Description：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyTVHolder> {

    List<UserBean> lists;

    private AsyncListDiffer<UserBean> mDiffer;

    public MyAdapter() {

        mDiffer = new AsyncListDiffer<UserBean>(this, new MyAsyncListDiffer());
    }

    public void setDatas(List<UserBean> userBeans) {
        this.lists = userBeans;
        mDiffer.submitList(userBeans);
    }

    @NonNull
    @Override
    public MyTVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitem, parent, false);

        return new MyTVHolder(myItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTVHolder holder, int position) {
        Log.i("MyAdapter", "position = " + position);

        holder.mTextView1.setText(lists.get(position).id);
        holder.mTextView2.setText(lists.get(position).name);
        holder.mTextView3.setText(lists.get(position).mail);

        holder.itemLayout.setBackgroundColor(Color.parseColor(getRandColor()));
    }

    @Override
    public void onBindViewHolder(@NonNull MyTVHolder holder, int position, @NonNull List<Object> payloads) {

        // 只有当Diffutil中的getChangePayload实现后，才会执行
        if (payloads.size() > 0) {
            Bundle bundle = (Bundle) payloads.get(0);
            String name = bundle.getString("name");
            String mail = bundle.getString("mail");
            if (!TextUtils.isEmpty(name)) {
                holder.mTextView2.setText(name);
            }

            if (!TextUtils.isEmpty(mail)) {
                holder.mTextView3.setText(mail);
            }

            holder.itemLayout.setBackgroundColor(Color.WHITE);
        } else {
            super.onBindViewHolder(holder, position, payloads);
        }

    }

    class Myaaa implements ExecutorService {

        /**
         * Initiates an orderly shutdown in which previously submitted
         * tasks are executed, but no new tasks will be accepted.
         * Invocation has no additional effect if already shut down.
         *
         * <p>This method does not wait for previously submitted tasks to
         * complete execution.  Use {@link #awaitTermination awaitTermination}
         * to do that.
         */
        @Override
        public void shutdown() {

        }

        /**
         * Attempts to stop all actively executing tasks, halts the
         * processing of waiting tasks, and returns a list of the tasks
         * that were awaiting execution.
         *
         * <p>This method does not wait for actively executing tasks to
         * terminate.  Use {@link #awaitTermination awaitTermination} to
         * do that.
         *
         * <p>There are no guarantees beyond best-effort attempts to stop
         * processing actively executing tasks.  For example, typical
         * implementations will cancel via {@link Thread#interrupt}, so any
         * task that fails to respond to interrupts may never terminate.
         *
         * @return list of tasks that never commenced execution
         */
        @Override
        public List<Runnable> shutdownNow() {
            return null;
        }

        /**
         * Returns {@code true} if this executor has been shut down.
         *
         * @return {@code true} if this executor has been shut down
         */
        @Override
        public boolean isShutdown() {
            return false;
        }

        /**
         * Returns {@code true} if all tasks have completed following shut down.
         * Note that {@code isTerminated} is never {@code true} unless
         * either {@code shutdown} or {@code shutdownNow} was called first.
         *
         * @return {@code true} if all tasks have completed following shut down
         */
        @Override
        public boolean isTerminated() {
            return false;
        }

        /**
         * Blocks until all tasks have completed execution after a shutdown
         * request, or the timeout occurs, or the current thread is
         * interrupted, whichever happens first.
         *
         * @param timeout the maximum time to wait
         * @param unit    the time unit of the timeout argument
         * @return {@code true} if this executor terminated and
         * {@code false} if the timeout elapsed before termination
         * @throws InterruptedException if interrupted while waiting
         */
        @Override
        public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
            return false;
        }

        /**
         * Submits a value-returning task for execution and returns a
         * Future representing the pending results of the task. The
         * Future's {@code get} method will return the task's result upon
         * successful completion.
         *
         * <p>
         * If you would like to immediately block waiting
         * for a task, you can use constructions of the form
         * {@code result = exec.submit(aCallable).get();}
         *
         * <p>Note: The {@link Executors} class includes a set of methods
         * that can convert some other common closure-like objects,
         * for example, {@link PrivilegedAction} to
         * {@link Callable} form so they can be submitted.
         *
         * @param task the task to submit
         * @return a Future representing pending completion of the task
         * @throws RejectedExecutionException if the task cannot be
         *                                    scheduled for execution
         * @throws NullPointerException       if the task is null
         */
        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return null;
        }

        /**
         * Submits a Runnable task for execution and returns a Future
         * representing that task. The Future's {@code get} method will
         * return the given result upon successful completion.
         *
         * @param task   the task to submit
         * @param result the result to return
         * @return a Future representing pending completion of the task
         * @throws RejectedExecutionException if the task cannot be
         *                                    scheduled for execution
         * @throws NullPointerException       if the task is null
         */
        @Override
        public <T> Future<T> submit(Runnable task, T result) {
            return null;
        }

        /**
         * Submits a Runnable task for execution and returns a Future
         * representing that task. The Future's {@code get} method will
         * return {@code null} upon <em>successful</em> completion.
         *
         * @param task the task to submit
         * @return a Future representing pending completion of the task
         * @throws RejectedExecutionException if the task cannot be
         *                                    scheduled for execution
         * @throws NullPointerException       if the task is null
         */
        @Override
        public Future<?> submit(Runnable task) {
            return null;
        }

        /**
         * Executes the given tasks, returning a list of Futures holding
         * their status and results when all complete.
         * {@link Future#isDone} is {@code true} for each
         * element of the returned list.
         * Note that a <em>completed</em> task could have
         * terminated either normally or by throwing an exception.
         * The results of this method are undefined if the given
         * collection is modified while this operation is in progress.
         *
         * @param tasks the collection of tasks
         * @return a list of Futures representing the tasks, in the same
         * sequential order as produced by the iterator for the
         * given task list, each of which has completed
         * @throws InterruptedException       if interrupted while waiting, in
         *                                    which case unfinished tasks are cancelled
         * @throws NullPointerException       if tasks or any of its elements are {@code null}
         * @throws RejectedExecutionException if any task cannot be
         *                                    scheduled for execution
         */
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
            return null;
        }

        /**
         * Executes the given tasks, returning a list of Futures holding
         * their status and results
         * when all complete or the timeout expires, whichever happens first.
         * {@link Future#isDone} is {@code true} for each
         * element of the returned list.
         * Upon return, tasks that have not completed are cancelled.
         * Note that a <em>completed</em> task could have
         * terminated either normally or by throwing an exception.
         * The results of this method are undefined if the given
         * collection is modified while this operation is in progress.
         *
         * @param tasks   the collection of tasks
         * @param timeout the maximum time to wait
         * @param unit    the time unit of the timeout argument
         * @return a list of Futures representing the tasks, in the same
         * sequential order as produced by the iterator for the
         * given task list. If the operation did not time out,
         * each task will have completed. If it did time out, some
         * of these tasks will not have completed.
         * @throws InterruptedException       if interrupted while waiting, in
         *                                    which case unfinished tasks are cancelled
         * @throws NullPointerException       if tasks, any of its elements, or
         *                                    unit are {@code null}
         * @throws RejectedExecutionException if any task cannot be scheduled
         *                                    for execution
         */
        @Override
        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
            return null;
        }

        /**
         * Executes the given tasks, returning the result
         * of one that has completed successfully (i.e., without throwing
         * an exception), if any do. Upon normal or exceptional return,
         * tasks that have not completed are cancelled.
         * The results of this method are undefined if the given
         * collection is modified while this operation is in progress.
         *
         * @param tasks the collection of tasks
         * @return the result returned by one of the tasks
         * @throws InterruptedException       if interrupted while waiting
         * @throws NullPointerException       if tasks or any element task
         *                                    subject to execution is {@code null}
         * @throws IllegalArgumentException   if tasks is empty
         * @throws ExecutionException         if no task successfully completes
         * @throws RejectedExecutionException if tasks cannot be scheduled
         *                                    for execution
         */
        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws ExecutionException, InterruptedException {
            return null;
        }

        /**
         * Executes the given tasks, returning the result
         * of one that has completed successfully (i.e., without throwing
         * an exception), if any do before the given timeout elapses.
         * Upon normal or exceptional return, tasks that have not
         * completed are cancelled.
         * The results of this method are undefined if the given
         * collection is modified while this operation is in progress.
         *
         * @param tasks   the collection of tasks
         * @param timeout the maximum time to wait
         * @param unit    the time unit of the timeout argument
         * @return the result returned by one of the tasks
         * @throws InterruptedException       if interrupted while waiting
         * @throws NullPointerException       if tasks, or unit, or any element
         *                                    task subject to execution is {@code null}
         * @throws TimeoutException           if the given timeout elapses before
         *                                    any task successfully completes
         * @throws ExecutionException         if no task successfully completes
         * @throws RejectedExecutionException if tasks cannot be scheduled
         *                                    for execution
         */
        @Override
        public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws ExecutionException, InterruptedException, TimeoutException {
            return null;
        }

        /**
         * Executes the given command at some time in the future.  The command
         * may execute in a new thread, in a pooled thread, or in the calling
         * thread, at the discretion of the {@code Executor} implementation.
         *
         * @param command the runnable task
         * @throws RejectedExecutionException if this task cannot be
         *                                    accepted for execution
         * @throws NullPointerException       if command is null
         */
        @Override
        public void execute(Runnable command) {

        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull MyTVHolder holder) {
        super.onViewAttachedToWindow(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return null == lists ? 0 : lists.size();
    }

    public static class MyTVHolder extends RecyclerView.ViewHolder {
        public LinearLayout itemLayout;
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;

        public MyTVHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.item_layout);
            mTextView1 = itemView.findViewById(R.id.tv_id);
            mTextView2 = itemView.findViewById(R.id.tv_name);
            mTextView3 = itemView.findViewById(R.id.tv_mail);
        }
    }

    class a extends RecyclerView.ViewHolder {

        public a(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 获取十六进制的颜色代码.例如  "#5A6677"
     * 分别取R、G、B的随机值，然后加起来即可
     *
     * @return String
     */
    public static String getRandColor() {
        String R, G, B;
        Random random = new Random();
        R = Integer.toHexString(random.nextInt(256)).toUpperCase();
        G = Integer.toHexString(random.nextInt(256)).toUpperCase();
        B = Integer.toHexString(random.nextInt(256)).toUpperCase();

        R = R.length() == 1 ? "0" + R : R;
        G = G.length() == 1 ? "0" + G : G;
        B = B.length() == 1 ? "0" + B : B;

        return "#" + R + G + B;
    }
}
