package cassetteTapeConverter;

public class NoiseReduction {

    private static String OS = System.getProperty("os.name").toLowerCase();
    public static void runNoiseReduction() {
        //public static void reduceNoise(){
        if (isWindows()) {
            try{
                Process p = Runtime.getRuntime().exec("cmd /c \"sox Full_Audio.wav noise-audio.wav trim 0 5.0 && sox noise-audio.wav -n noiseprof noise.prof && sox Full_Audio.wav audio-clean.wav noisered noise.prof 0.21\"");
                //Process q = Runtime.getRuntime().exec("cmd /d \"sox Full_Audio.wav noise-audio.wav trim 0 5.0 && sox noise-audio.wav -n noiseprof noise.prof && sox Full_Audio.wav audio-clean.wav noisered noise.prof 0.21\"");
                //Process r = Runtime.getRuntime().exec("cmd /e \"sox Full_Audio.wav noise-audio.wav trim 0 5.0 && sox noise-audio.wav -n noiseprof noise.prof && sox Full_Audio.wav audio-clean.wav noisered noise.prof 0.21\"");
                p.waitFor();
            }
            catch(Exception e){
                System.out.println("Error in the windows noise reduction!");
                System.out.println(e.getMessage());

            }
        }
        //}

        //public static void reduceNoiseInBash(){
        else if(isUnix()) {
            try{

                String NRvalue = Double.toString(AudioSplitter.getNR());

                Process p2 = Runtime.getRuntime().exec("sox Full_Audio.wav -n trim 0 5.0 noiseprof audio.noise-profile");
                p2.waitFor();
                Process p3 = Runtime.getRuntime().exec("sox Full_Audio.wav audio-clean.wav noisered audio.noise-profile " + NRvalue);
                p3.waitFor();
            }
            catch(Exception e){
                System.out.println("Error trying to do noise reduction in Linux.");
                System.out.println(e.getMessage());
            }
        }
        // }
    }
    public static boolean isWindows() {
        return OS.contains("win");
    }

    public static boolean isMac() {
        return OS.contains("mac");
    }

    public static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
    }

    public static boolean isSolaris() {
        return OS.contains("sunos");
    }
    public static String getOS(){
        if (isWindows()) {
            return "win";
        } else if (isMac()) {
            return "osx";
        } else if (isUnix()) {
            return "uni";
        } else if (isSolaris()) {
            return "sol";
        } else {
            return "err";
        }
    }
}

//sox audio.wav noise-audio.wav trim 0 0.900

//sox noise-audio.wav -n noiseprof noise.prof

//sox audio.wav audio-clean.wav noisered noise.prof 0.21

//sox Full_Audio.wav -n trim 0 5.0 noiseprof audio.noise-profile

//sox Full_Audio.wav audio-clean.wav noisered audio.noise-profile 0.3



