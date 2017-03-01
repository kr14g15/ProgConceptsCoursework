import javafx.scene.paint.Color;

public enum ColorScheme {
        //for normal users
        DEFAULT(Color.rgb(34,139,34), Color.rgb(222,184,135), Color.rgb(178,178,254),
                Color.rgb(144,238,144),Color.rgb(128,128,128),Color.rgb(255,255,255),Color.rgb(0,0,0)),

        //for users with color-blindeness
        COLOR_BLIND(Color.rgb(36,255,36), Color.rgb(146,73,0), Color.rgb(73,0,146),
                    Color.rgb(0,146,146),Color.rgb(146,0,0),Color.rgb(255,255,255),Color.rgb(0,0,0));

        public Color BackgroundColor;
        public Color GroundColor;
        public Color SkyColor;
        public Color CAAColor;
        public Color RunwayStripColor;
        public Color StripesColor;
        public Color CharactersColor;

        private ColorScheme(Color backgroundColor, Color groundColor, Color skyColor, Color CAAColor,
                            Color runwayStripColor,Color stripesColor,Color charactersColor)
        {
            this.BackgroundColor = backgroundColor;
            this.GroundColor = groundColor;
            this.SkyColor = skyColor;
            this.CAAColor = CAAColor;
            this.RunwayStripColor = runwayStripColor;
            this.StripesColor = stripesColor;
            this.CharactersColor = charactersColor;
        }
}
