package com.example.dell.benahapp.Major;


public class visionFactory {

    public static String MISSIONS [] = {


            " is committed to the graduation of competent doctors, unable to compete" +
                    " and higher scientific, and undertake to support scientific research to serve the development" ,



            " is committed to the graduation of competent doctors, for higher animal health care." ,




            "Assuring the standards of Quality and Accreditation" +
                    "Cooperating and collaborating with other national " ,


            "is commited to prepare" +
                    "graduates with the knowledge and skills that qualify them to compete in the labor market," ,


            "would have a role in developing the community by providing an stimulating " +
                    "environment for education and scientific research as well as providing distinguished educational service",


            " is committed to the graduation of competent doctors, for higher animal health care." ,

            "would have a role in developing the community by providing an stimulating " +
                    "environment for  providing distinguished educational service",


            " is committed to the graduation of competent trainers, unable to compete" +
                    " and higher phisical, ",


            "seeks to be a regional leader in law and community participation. " +
                    "The Faculty seeks also to create a generation that is able to " +
                    "compete ",

            "is commited to prepare" +
                    "graduates with the knowledge and skills that qualify them to compete in the labor market," +
                    " is also committed to the production of scientific research as " +
                    " community services" ,


            "is commited to compete in the labor market," +
                    " is also committed to the production of scientific research as a privileged location at" +
                    " the international level " ,



            " is committed to the graduation of competent designers",



            " is committed to the graduation of competent doctors, for higher animal health care." ,

            " is committed to the graduation of competent designers"

    };

    public static String VISIONS [] = {

            " is seeking for excellence in medical education and learning, research and innovation," +
                    " influenced by and influential in the surrounding environment . " ,



            " is seeking for  medical education for animal care " +
                    "and influential in  promoting life at home. ",


             "aims at achieving distinction in education and research in a democratic and independent atmosphere .",

            "is looking forward to be " +
                    "pilot college in the fields of science Educatio" +
                    " at the regional levels and to community service." ,



            "Vision aims to make Benha University as a leading example for the Egypt's universities in education, " ,


            "aims at achieving distinction in education and research in a democratic and independent atmosphere .",


            "Vision aims to make Benha University  reach to international competition in some fields." ,


            " is seeking for , research and innovation," +
                    " influenced by and influential in the surrounding environment and promoting life at home. " ,

            " The faculty does its best to prepare researchers that are" +
                    " able to conduct legal researchers to serve the local ",

            "is looking forward to be " +
                    "pilot college in the fields of Engineering Education and Scientific Research" +
                    "at the regional and international levels .",

            "is looking forward to be " +
                    "pilot college in the fields of computer science",

            "aims at achieving distinction in western and eastern designs .",

            "aims at achieving distinction in education and research in a democratic and independent atmosphere .",

            "aims at achieving distinction in western and eastern designs ."

    };

    public static String NAMES [] = {
            "Faculty of Medicine" ,
            "Faculty of Veterinary",
            "Faculty of Nursing",
            "Faculty of Science",
            "Faculty of education",
            "Faculty of Arts",
            "Faculty of Agriculture",
            "Faculty of Physical Education",
            "Faculty of Law",
            "Shoubra Faculty of engineering",
            "Faculty of Information and Technology" ,
            "Benha Faculty of Engineering" ,
            "Faculty of Applied Arts",
            "Faculty of Specific Education"
    };


    public static void  fill_names(String...names)
    {

        for (int i = 0; i < NAMES.length; i++) {
            NAMES[i] = names[i];
        }
    }

    public static int getIndex(String x)
    {
        for (int i = 0; i < NAMES.length; i++) {
            if(NAMES[i].equals(x))
                return i;
        }
        return 0;
    }

    public static String getMission(String x)
    {
        int i = getIndex(x);
        return  MISSIONS[i];
    }

    public static String getVision(String x)
    {
        int i = getIndex(x);
        return  VISIONS[i];
    }
}
