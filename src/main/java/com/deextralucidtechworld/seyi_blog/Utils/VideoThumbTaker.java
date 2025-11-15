// package com.deextralucidtechworld.seyi_blog.Utils;

// import java.io.IOException;
// import java.nio.file.DirectoryStream;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.UUID;


// // import java.io.BufferedReader;
// // import java.io.File;
// // import java.io.IOException;
// // import java.io.InputStream;
// // import java.io.InputStreamReader;


// // public class VideoThumbTaker {

// // protected String ffmpegApp;

// // public VideoThumbTaker(String ffmpegApp) {
// // this.ffmpegApp = ffmpegApp;
// // }

// // public void getThumb(String videoFilename, String thumbFilename, int
// width,
// // int height, int hour, int min,
// // float sec)
// // throws IOException, InterruptedException {
// // ProcessBuilder processBuilder = new ProcessBuilder(ffmpegApp, "-y", "-i",
// // videoFilename, "-vframes", "1", "-ss",
// // hour + ":" + min + ":" + sec, "-f", "mjpeg", "-s", width + "*" + height,
// // "-an", thumbFilename);
// // Process process = processBuilder.start();
// // InputStream stderr = process.getErrorStream();
// // InputStreamReader isr = new InputStreamReader(stderr);
// // BufferedReader br = new BufferedReader(isr);
// // String line;
// // while ((line = br.readLine()) != null)
// // process.waitFor();
// // }

// // public static void main(String[] args) throws Exception, IOException {
// // FFmpegFrameGrabber g = new FFmpegFrameGrabber(
// //
// "C:\\Users\\EPHRAIN\\blog-site-backend\\seyi_blog\\src\\main\\resources\\videos\\Asake-Terminator.mp4");
// // g.setFormat("mp4");
// // g.start();
// // for (int i = 0; i < 50; i++) {
// // ImageIO.write(g.grab().getBufferedImage(), "png", new File(
// //
// "C:\\Users\\EPHRAIN\\blog-site-backend\\seyi_blog\\src\\main\\resources\\thumbnails\\video-frame-"
// // + System.currentTimeMillis() + ".png"));
// // }
// // g.stop();
// // }

// // }

// public class VideoThumbTaker {

// // private static String dirString;

// // public VideoThumbTaker(String dirString) {
// // this.dirString = dirString;
// // }

// public static void main(String[] args) throws IOException, JCodecException {
// int frameNumber = 0;
// DirectoryStream.Filter<Path> filter = file -> {
// return file.toString().endsWith(".mp4") || file.toString().endsWith(".MP4")
// || file.toString().endsWith(".mov") || file.toString().endsWith(".MOV");
// };

// Path dirName =
// Paths.get("C:\\Users\\EPHRAIN\\blog-site-backend\\seyi_blog\\src\\main\\resources\\videos\\");

// try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirName,
// filter)) {
// stream.forEach(path -> {
// try {
// Picture picture = FrameGrab.getFrameFromFile(new File(path.toString()),
// frameNumber);
// BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
// ImageIO.write(bufferedImage, "png", new File(
// "C:\\Users\\EPHRAIN\\blog-site-backend\\seyi_blog\\src\\main\\resources\\thumbnails\\video-frame-"
// + UUID.randomUUID().toString() + ".png"));
// } catch (Exception e) {
// e.printStackTrace();
// }
// });
// } catch (IOException e) {
// e.printStackTrace();
// }
// }
// }