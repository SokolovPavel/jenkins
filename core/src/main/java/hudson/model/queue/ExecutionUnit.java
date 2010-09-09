/*
 * The MIT License
 *
 * Copyright (c) 2010, InfraDNA, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package hudson.model.queue;

import hudson.model.Executor;
import hudson.model.Label;
import hudson.model.Node;
import hudson.model.Queue.Executable;
import hudson.model.Queue.Task;
import hudson.model.ResourceActivity;

import java.io.IOException;

/**
 * A component of {@link Task} that represents a computation carried out by a single {@link Executor}.
 *
 * A {@link Task} consists of a number of {@link ExecutionUnit}.
 *
 * @since 1.FATTASK
 */
public interface ExecutionUnit extends ResourceActivity {
    /**
     * If this task needs to be run on a node with a particular label,
     * return that {@link Label}. Otherwise null, indicating
     * it can run on anywhere.
     */
    Label getAssignedLabel();

    /**
     * If the previous execution of this task run on a certain node
     * and this task prefers to run on the same node, return that.
     * Otherwise null.
     */
    Node getLastBuiltOn();

    /**
     * Estimate of how long will it take to execute this task.
     * Measured in milliseconds.
     *
     * @return -1 if it's impossible to estimate.
     */
    long getEstimatedDuration();

    /**
     * Creates {@link Executable}, which performs the actual execution of the task.
     */
    Executable createExecutable() throws IOException;

    /**
     * If a subset of {@link ExecutionUnit}s of a {@link Task} needs to be collocated with other {@link ExecutionUnit}s,
     * those {@link ExecutionUnit}s should return the equal object here. If null, the execution unit isn't under a
     * colocation constraint.
     *
     * @since 1.FATTASK
     */
    Object getSameNodeConstraint();
}
