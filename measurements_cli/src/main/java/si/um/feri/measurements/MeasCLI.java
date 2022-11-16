package si.um.feri.measurements;

import picocli.CommandLine;

@CommandLine.Command(
        name = "MeasCLI",
        version = "0.0.1",
        mixinStandardHelpOptions = true,
        subcommands = {ListCommand.class, AddProductCommand.class}
)
public class MeasCLI implements Runnable {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new MeasCLI()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("""
        -= MeasCLI =-
        Usage examples:
            MeasCLI --help
            MeasCLI list products -n
            MeasCLI list products -n -s http://127.0.0.1:8280
            MeasCLI addproduct IceCream -18 -5.5
        """);
    }

}