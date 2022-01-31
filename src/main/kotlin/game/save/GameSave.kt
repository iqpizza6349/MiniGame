package game.save

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption
import java.time.LocalDateTime
import java.util.*

class GameSave {

    /*
    classPath 위치에 디렉토리 생성 및 파일을 저장합니다.
     */

    companion object {

        fun save(gameType: String, text: String) {
            val absolutePath = File("").absolutePath + "\\"
            val path = "save\\$gameType"

            val file = File(path)
            if (!file.exists()) {
                file.mkdirs()
            }

            val dateTime = LocalDateTime.now(TimeZone.getDefault().toZoneId())
            val year = dateTime.year.toString()
            val month = dateTime.monthValue.toString()
            val date = dateTime.dayOfMonth.toString()

            val hour = dateTime.hour.toString()
            val minute = dateTime.minute.toString()
            val second = dateTime.second.toString()

            val fileName = "$year$month${date}_$hour$minute${second}.txt"

            try {
                Files.write(Paths.get("$absolutePath$path/$fileName"), text.toByteArray(), StandardOpenOption.CREATE)
            } catch (e: IOException ) {
                e.stackTrace
            }
        }
        
    }

}