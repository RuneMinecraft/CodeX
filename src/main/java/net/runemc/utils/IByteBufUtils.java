package net.runemc.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public interface IByteBufUtils {
    default int readVarInt(ByteBuf buf) {
        int value = 0;
        int position = 0;
        byte currentByte;

        do {
            if (position >= 32) {
                throw new RuntimeException("VarInt is too big");
            }
            currentByte = buf.readByte();
            value |= (currentByte & 0x7F) << position;
            position += 7;
        } while ((currentByte & 0x80) == 0x80);

        return value;
    }
    default void writeVarInt(ByteBuf buf, int value) {
        while ((value & 0xFFFFFF80) != 0L) {
            buf.writeByte((value & 0x7F) | 0x80);
            value >>>= 7;
        }
        buf.writeByte(value & 0x7F);
    }

    default String readString(ByteBuf buf) {
        int length = readVarInt(buf);
        if (length > 32767) {
            throw new RuntimeException("String too long: " + length + " bytes");
        }
        if (length > buf.readableBytes()) {
            throw new IndexOutOfBoundsException("String length (" + length + ") exceeds readable bytes (" + buf.readableBytes() + ")");
        }

        byte[] bytes = new byte[length];
        buf.readBytes(bytes); // Read the string bytes
        return new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
    }
    default void writeString(ByteBuf buf, String value) {
        byte[] bytes = value.getBytes(java.nio.charset.StandardCharsets.UTF_8);
        if (bytes.length > 32767) {
            throw new RuntimeException("String too long: " + bytes.length + " bytes");
        }
        writeVarInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    default void writeStringVI(ByteBuf out, String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(out, bytes.length);  // Write the length of the string as a VarInt
        out.writeBytes(bytes);           // Write the string data
    }

    default String readStringVI(ByteBuf in) {
        int length = readVarInt(in);     // Read the length as a VarInt
        byte[] bytes = new byte[length];
        in.readBytes(bytes);             // Read the string data
        return new String(bytes, StandardCharsets.UTF_8);  // Convert to String
    }
}
