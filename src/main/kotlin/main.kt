
data class Chat(
    val messages: MutableList<Message> = mutableListOf()

)
data class Message(
    val userId: Int,
    val text: String,
    val income: Boolean = false,
    var read: Boolean = false,
    )

class ChatNotFound : RuntimeException()

object ChatService {
    val chats = mutableMapOf<Int, Chat>()

    // Добавить сообщение
    fun addMessage(userId: Int, message: Message) : Boolean {
        return chats.getOrPut(userId) {Chat()}.messages.add(message)
    }
    // Удалить сообщение из чата
    fun deleteMessage(userId: Int, messageIndex: Int) : Message? {
        return chats[userId]?.messages?.removeAt(messageIndex)

    }
        // Удалить чат
    fun deleteChat(userId: Int) : Chat? = chats.remove(userId)
     // Получить список чатов
    fun getChats() = chats.values.toList()
    // Получить количество непрочитанных чатов
    fun getUnreadChatsCount() = chats.values.count { chat -> chat.messages.any { message -> !message.read && message.income} }
     // Получить последние непрочитанные сообщения
    fun getLastMessagesAllChats() = chats.values.map { chat -> chat.messages.lastOrNull()?.text ?: "No messages" }
     // получить список  сообщений из чата
    fun getMessagesInChat(userId: Int, count: Int): List<Message>? {
       return chats[userId]?.messages?.takeLast(count)?.onEach { it.read = true }
    }


}


fun main (){
    ChatService.addMessage(1, Message(1,"hi"))
    //ChatService.addMessage(1, Message(1,"hi", true))
    //ChatService.addMessage(1, Message(1,"2"))
    //ChatService.addMessage(1, Message(1,"3"))
    ChatService.addMessage(2, Message(2,"Hello", true))
    //ChatService.addMessage(3, Message(2,"Hello"))
    println(ChatService.getUnreadChatsCount())

}