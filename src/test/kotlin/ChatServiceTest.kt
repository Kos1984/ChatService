import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class ChatServiceTest {

    @Test
    fun addMessage() {

        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        assert(true)
    }

    @Test
    fun deleteMessage() {
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        val result = ChatService.deleteMessage(1, 0)
        assertEquals(message, result)

    }
      @Test
    fun deleteChat() {
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
          val temp = ChatService.deleteChat(1)
          val result = temp!!.messages[0]
        assertEquals(result, Message(1,"hi", read = true ))
    }

    @Test
    fun getChats(){
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        val data = ChatService.chats.values.toList()
        val result = ChatService.getChats()
        assertEquals(data, result)
    }

    @Test
    fun getUnreadChatsCount() {
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        val  result = ChatService.getUnreadChatsCount()
        assertEquals(0, result)
    }

    @Test
    fun getLastMessagesAllChats(){
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        val result = ChatService.getLastMessagesAllChats()
        assertEquals(listOf("hi") ,result)
    }

    @Test
    fun getMessagesInChat(){
        val message = Message(1, "hi")
        ChatService.addMessage(1, message)
        val result = ChatService.getMessagesInChat(1,1)
        assertEquals(message, result?.get(0))
    }
}