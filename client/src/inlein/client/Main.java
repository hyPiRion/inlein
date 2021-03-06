package inlein.client;

import java.util.*;
import java.io.*;
import java.net.*;
import com.hypirion.bencode.*;

import inlein.client.tasks.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String task;
        String[] taskArgs;
        if (args.length == 0) {
            task = "--help";
            taskArgs = new String[0];
        }
        else if (! args[0].startsWith("--")) {
            task = "--run";
            taskArgs = args;
        } else {
            task = args[0];
            taskArgs = Arrays.copyOfRange(args, 1, args.length);
        }

        Task t = tasks.get(task);
        if (t == null) {
            System.out.println("Inlein has no task with name " + task);
            System.exit(1);
        }

        t.run(null, taskArgs);
        System.exit(0);
    }


    public static final TreeMap<String, Task> tasks = new TreeMap<String, Task>();

    static {
        Help h = new Help(tasks);
        tasks.put(h.taskname, h);
        tasks.put(Version.instance.taskname, Version.instance);
        tasks.put(Ping.instance.taskname, Ping.instance);
        tasks.put(Run.instance.taskname, Run.instance);
        tasks.put(Repl.instance.taskname, Repl.instance);
        tasks.put(ShutdownDaemon.instance.taskname, ShutdownDaemon.instance);
        tasks.put(StartDaemon.instance.taskname, StartDaemon.instance);
        tasks.put(RestartDaemon.instance.taskname, RestartDaemon.instance);
        tasks.put(Deps.instance.taskname, Deps.instance);
        tasks.put(ShCmd.instance.taskname, ShCmd.instance);
        tasks.put(Upgrade.instance.taskname, Upgrade.instance);
    }
}
