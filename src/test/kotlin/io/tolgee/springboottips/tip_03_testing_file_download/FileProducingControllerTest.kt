package io.tolgee.springboottips.tip_03_testing_file_download

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
internal class FileProducingControllerTest {
    @field:Autowired
    lateinit var mvc: MockMvc

    @Test
    fun `produces correct body`() {
        val result = mvc.perform(
                MockMvcRequestBuilders.get("/api/v1/03/get-file")
        )
                .andDo { it.asyncResult }
                .andReturn()
        val content = result.response.contentAsString
        assertEquals("a".repeat(1000000), content)
    }
}
