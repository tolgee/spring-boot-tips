package io.tolgee.springboottips.tip_03_testing_file_download

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.OutputStream

@Controller
@RequestMapping(value = ["/api/v1/03/get-file"])
class FileProducingController {
    @GetMapping(value = [""], produces = ["application/text"])
    fun export(): ResponseEntity<StreamingResponseBody> {

        return ResponseEntity
                .ok()
                // set a filename
                .header(
                        "Content-Disposition",
                        """attachment; filename="%file.txt""""
                )
                .body(
                        // stream the body
                        StreamingResponseBody { out: OutputStream ->
                            val text = "a".repeat(1000000)
                            out.write(text.toByteArray())
                            out.close()
                        }
                )
    }
}
