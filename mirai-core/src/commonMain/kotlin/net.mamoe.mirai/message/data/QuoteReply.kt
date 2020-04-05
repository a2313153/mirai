/*
 * Copyright 2020 Mamoe Technologies and contributors.
 *
 * 此源代码的使用受 GNU AFFERO GENERAL PUBLIC LICENSE version 3 许可证的约束, 可以在以下链接找到该许可证.
 * Use of this source code is governed by the GNU AGPLv3 license that can be found through the following link.
 *
 * https://github.com/mamoe/mirai/blob/master/LICENSE
 */

@file:JvmMultifileClass
@file:JvmName("MessageUtils")
@file:Suppress("NOTHING_TO_INLINE")

package net.mamoe.mirai.message.data

import kotlinx.coroutines.Job
import net.mamoe.mirai.message.MessageReceipt
import net.mamoe.mirai.utils.MiraiExperimentalAPI
import net.mamoe.mirai.utils.SinceMirai
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName


/**
 * 引用回复.
 *
 * 可以引用一条群消息并发送给一个好友, 或是引用好友消息发送给群.
 * 可以引用自己发出的消息. 详见 [MessageReceipt.quote]
 *
 * @see MessageSource 获取更多信息
 */
@OptIn(MiraiExperimentalAPI::class)
@SinceMirai("0.33.0")
class QuoteReply(val source: MessageSource) : Message, MessageMetadata, ConstrainSingle<QuoteReply> {
    // TODO: 2020/4/4 Metadata or Content?
    companion object Key : Message.Key<QuoteReply>

    override val key: Message.Key<QuoteReply> get() = Key

    override fun toString(): String = "[mirai:quote:${source.id}]"
    override fun contentToString(): String = ""
}

suspend inline fun QuoteReply.recall() = this.source.recall()

inline fun QuoteReply.recallIn(
    millis: Long,
    coroutineContext: CoroutineContext = EmptyCoroutineContext
): Job = this.source.recallIn(millis, coroutineContext)