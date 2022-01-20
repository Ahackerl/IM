package com.netty.nettyclass;

import com.netty.service.impl.ChatService;
import com.netty.service.impl.UserService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GroupChatHandler implements ChannelHandler, EventExecutorGroup {
    public GroupChatHandler(UserService userService, ChatService chatService) {
    }

    @Override
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }

    @Override
    public boolean isShuttingDown() {
        return false;
    }

    @Override
    public Future<?> shutdownGracefully() {
        return null;
    }

    @Override
    public Future<?> shutdownGracefully(long l, long l1, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public Future<?> terminationFuture() {
        return null;
    }

    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, @org.jetbrains.annotations.NotNull TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public EventExecutor next() {
        return null;
    }

    @Override
    public Iterator<EventExecutor> iterator() {
        return null;
    }

    @Override
    public Future<?> submit(Runnable runnable) {
        return null;
    }

    @NotNull
    @Override
    public <T> List<java.util.concurrent.Future<T>> invokeAll(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return null;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> List<java.util.concurrent.Future<T>> invokeAll(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks, long timeout, @org.jetbrains.annotations.NotNull TimeUnit unit) throws InterruptedException {
        return null;
    }

    @org.jetbrains.annotations.NotNull
    @Override
    public <T> T invokeAny(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public <T> T invokeAny(@org.jetbrains.annotations.NotNull Collection<? extends Callable<T>> tasks, long timeout, @org.jetbrains.annotations.NotNull TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public <T> Future<T> submit(Runnable runnable, T t) {
        return null;
    }

    @Override
    public <T> Future<T> submit(Callable<T> callable) {
        return null;
    }

    @Override
    public ScheduledFuture<?> schedule(Runnable runnable, long l, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long l, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long l, long l1, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long l, long l1, TimeUnit timeUnit) {
        return null;
    }

    @Override
    public void execute(@org.jetbrains.annotations.NotNull Runnable command) {

    }
}
