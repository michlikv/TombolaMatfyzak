package main;


public enum Logger {
    errWindowCouldNotLoad0("Okno se nepodařilo otevřít."),
    windowErrWinNotFound1("Soubor nenalezen: %s"),
    FormatError1("Špatný formát vstupu: %s"),
    FormatError2("Špatný formát vstupu: %s, %s"),
    ;
    
    
    private final String text;
    
    Logger(String text)
    { this.text = text;}
    
    public String getAsFormattedText(Object... o) {
        return String.format(text, o);
    }
    
    public synchronized void PrintAsFormattedText(Object... o)
    {
        System.out.println(getAsFormattedText(o));
    }    
}
