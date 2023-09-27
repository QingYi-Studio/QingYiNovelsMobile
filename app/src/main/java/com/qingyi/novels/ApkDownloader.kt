import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class ApkDownloader(private val mContext: Context) : AsyncTask<String, Int, String>() {
    companion object {
        private const val TAG = "ApkDownloader"
    }

    @Deprecated("Deprecated in Java")
    override fun doInBackground(vararg params: String): String? {
        val apkUrl = params[0]
        return try {
            val url = URL(apkUrl)
            val conn = url.openConnection() as HttpURLConnection
            conn.connect()

            val fileLength = conn.contentLength

            val apkDir = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path)
            if (!apkDir.exists()) {
                apkDir.mkdirs()
            }

            val apkFile = File(apkDir, "app.apk")
            val fos = FileOutputStream(apkFile)
            val inputStream = conn.inputStream

            val buffer = ByteArray(1024)
            var len: Int
            var total: Long = 0
            while (inputStream.read(buffer).also { len = it } != -1) {
                fos.write(buffer, 0, len)
                total += len.toLong()
                publishProgress((total * 100 / fileLength).toInt())
            }

            fos.flush()
            fos.close()
            inputStream.close()

            apkFile.absolutePath
        } catch (e: IOException) {
            Log.e(TAG, "Error downloading APK: ${e.message}")
            null
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onProgressUpdate(vararg values: Int?) {
        // 在UI上更新下载进度
        val progress = values[0]
        // ...
    }

    @Deprecated("Deprecated in Java")
    override fun onPostExecute(apkFilePath: String?) {
        if (apkFilePath != null) {
            installApk(apkFilePath)
        } else {
            Log.e(TAG, "Failed to download APK")
            // 处理下载失败的逻辑
        }
    }

    private fun installApk(apkFilePath: String) {
        val apkFile = File(apkFilePath)
        val apkUri = Uri.fromFile(apkFile)

        val installIntent = Intent(Intent.ACTION_VIEW)
        installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive")
        installIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        mContext.startActivity(installIntent)
    }
}
