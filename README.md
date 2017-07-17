# ImageZipper
An image compresssion library in android.<br><br>
<img src="https://user-images.githubusercontent.com/12881364/28280492-83ac5b2a-6b41-11e7-9394-48ad8e8e44f1.jpg" align="middle" height=500/>
<br>
<p>ImageZipper is an image compression library which helps you to compress your images both in default and custom way. It allows
you to control you the width, height, format, orientation and quality of Image. It ease out the task to send images to the server.</p>

# Gradle
<p><b>Add this to your root build.gradle file:</b></p>

<pre><code>allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
</code></pre>

<p><b>Add this to your app module's build.gradle file:</b></p>
<pre><code>dependencies {
          compile 'com.github.amanjeetsingh150:ImageZipper:1.3'
    }
</code></pre>

  # Default Compressor
  <pre><code>
  File imageZipperFile=new ImageZipper(this).compressToFile(actualFile);
  </code></pre>
  
   # Custom Compressor
   <pre><code>
   File imageZipperFile=new ImageZipper(MainActivity.this)
                        .setQuality(10)
                        .setMaxWidth(200)
                        .setMaxHeight(200)
                        .compressToFile(actualFile);
  </code></pre>
  <p><b>OK Now Get me Bitmap!!</b></p>
   <pre><code>
   Bitmap b=new ImageZipper(MainActivity.this).compressToBitmap(actualFile);
   </code></pre>
   
   # Usage
   <p><b>LIST OF FUNCTIONS<p></b>
   <p>If you want to use custom compressor:<p>
   <table>
   <tr>
   <th>Function</th>
   <th>Arguments</th>
   </tr>
   <tr>
   <td>setMaxWidth(int maxWidth)</td>
   <td>Width required</td>
   </tr>
   <tr>
   <td>setMaxHeight(int maxHeight)</td>
   <td>Height required</td>
   </tr>
   <tr>
   <td>setMaxQuality(int quality)</td>
   <td>Quality required</td>
   </tr>
   <tr>
   <td>setOrientation(int maxHeight)</td>
   <td>Orientation required(90,180)</td>
   </tr>
   <tr>
   <td>setCompressFormat(Bitmap.CompressFormat compressFormat)</td>
   <td>Format required(PNG,JPEG,WEBP)</td>
   </tr>
   </table>
   <b><p>Cool !! Now I need Base64 Encoding and Decoding:<p></b>
   <pre>
   <code>
   String base64=ImageZipper.getBase64forImage(imageZipperFile);
   Bitmap b=ImageZipper.decodeBase64(String base64);
   </code>
   </pre>
   <b>NOTE:</b><br><br>
   <b>ImageZipper required permissions:</b>
   <b>Add this to your manifest:</b>
   <pre>
   <code>
   &ltuses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"&gt
   </code>
   </pre>
   <p>Marshmallow and above requests for the permission on runtime.</p>
   Refer to this:<br>
   https://blog.protektoid.com/android-permissions-kitkat-lollipop-and-marshmallow <br>
   https://stackoverflow.com/questions/33162152/storage-permission-error-in-marshmallow 
   <br><br>
   <b>DEVELOPERS<br>
<a href="https://github.com/amanjeetsingh150">Amanjeet Singh</a>
<br><br>
<b>LICENSE
<br>
<pre><code>Copyright 2017 Amanjeet Singh

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

</code></pre>

   
