package com.example.jy_game;

import java.util.List;

public class TranslationBean {

    /**
     * status : 0
     * content : {"ph_en":"rɪˈseptɪv","ph_am":"rɪˈsɛptɪv","ph_en_mp3":"http://res.iciba.com/resource/amp3/oxford/0/32/09/320962f149b8ffd259ce7177b7ccb056.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/29/96/2996808b62fb3ef0b0db48a341bc483e.mp3","ph_tts_mp3":"http://res-tts.iciba.com/2/9/9/2996808b62fb3ef0b0db48a341bc483e.mp3","word_mean":["adj. 善于接受的;能容纳的;有接受力的;感受的，感官的;"]}
     */

    private int status;
    private ContentBean content;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * ph_en : rɪˈseptɪv
         * ph_am : rɪˈsɛptɪv
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/oxford/0/32/09/320962f149b8ffd259ce7177b7ccb056.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/29/96/2996808b62fb3ef0b0db48a341bc483e.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/2/9/9/2996808b62fb3ef0b0db48a341bc483e.mp3
         * word_mean : ["adj. 善于接受的;能容纳的;有接受力的;感受的，感官的;"]
         */

        private String ph_en;
        private String ph_am;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<String> word_mean;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<String> getWord_mean() {
            return word_mean;
        }

        public void setWord_mean(List<String> word_mean) {
            this.word_mean = word_mean;
        }
    }
}
