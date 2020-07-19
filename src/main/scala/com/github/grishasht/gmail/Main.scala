package com.github.grishasht.gmail

import org.telegram.telegrambots.meta.TelegramBotsApi

object main extends App {

  var telegramBotsApi: TelegramBotsApi = new TelegramBotsApi()

  println(telegramBotsApi.toString)

  println("Works!")
}