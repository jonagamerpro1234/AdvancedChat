package jss.advancedchat.utils.logger;

import jss.advancedchat.files.utils.Settings;
import jss.advancedchat.utils.EventUtils;
import jss.advancedchat.utils.Util;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("all")
public class Logger {

    private static final File logFile = new File("plugins/AdvancedChat/logs.txt");

    private static void log(LoggerLevel level, String msg) {
        if (level == LoggerLevel.DEBUG && !Settings.config_debug) return;

        // Aplicar colores si está habilitado en la configuración
        String formattedMsg = Settings.enable_colors
                ? Util.getPrefixVar(level.getPrefix()) + " " + msg
                : level.getPrefix() + " " + msg;

        // Convertir '&' en '§' para compatibilidad con colores en chat
        formattedMsg = formattedMsg.replace("&", "§");

        // Enviar mensaje a la consola
        Util.sendColorMessage(EventUtils.getConsoleSender(), formattedMsg);

        // Guardar en archivo de log
        writeToFile(level, msg);
    }

    private static void writeToFile(LoggerLevel level, String msg) {
        try {
            if (!logFile.exists()) logFile.createNewFile();
            FileWriter writer = new FileWriter(logFile, true);
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write("[" + timeStamp + "] [" + level.name() + "] " + msg + "\n");
            writer.close();
        } catch (IOException e) {
            Util.sendColorMessage(EventUtils.getConsoleSender(), "§cError writing log file: " + e.getMessage());
        }
    }

    // Métodos de logging con niveles predefinidos
    public static void warning(String msg) { log(LoggerLevel.WARNING, msg); }
    public static void success(String msg) { log(LoggerLevel.SUCCESS, msg); }
    public static void error(String msg) { log(LoggerLevel.ERROR, msg); }
    public static void debug(String msg) { log(LoggerLevel.DEBUG, msg); }
    public static void info(String msg) { log(LoggerLevel.INFO, msg); }
    public static void outline(String msg) { log(LoggerLevel.OUTLINE, msg); }
    public static void chat(String msg) { log(LoggerLevel.CHAT, msg); }

    // Método para logs con excepciones detalladas
    public static void error(@NotNull Exception e, String msg) {
        log(LoggerLevel.ERROR, msg + " | Exception: " + e.getMessage());
        e.printStackTrace();
    }

    // Mensaje sin prefijo específico
    public static void defaultMessage(String msg) {
        String formattedMsg = Settings.enable_colors
                ? Util.getPrefix(true) + msg
                : msg;

        Util.sendColorMessage(EventUtils.getConsoleSender(), formattedMsg);
    }
}
