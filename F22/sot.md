```mermaid
flowchart TD

    root[Temporal clause] --> Q1{Time or circumstance?}
    Q1 --> circumstancial[/Circumstance/]
    Q1 --> temporal[/Time/]

    temporal --> indic[Indicative]
    indic --> indictens[Normal use of indicative tenses]

    circumstancial --> subj[Subjunctive]
    subj -->  Q2{Tense of main clause?}
    Q2 --> secondary[/Secondary sequence/]
    Q2 --> primary[/Primary sequence/]




    k1[Color key: mood]
    k2[Color key: tense]

    
    secondary --> Q3a{Time relation of subordinate clause}
    primary --> Q3b{Time relation of subordinate clause}

    
    Q3a --> same1[/Same time or later/]
    Q3a --> prev1[/Earlier/]
    Q3b --> same2[/Same time or later/]
    Q3b --> prev2[/Earlier/]

    same1 --> impft[Imperfect]
    prev1 --> ppft[Pluperfect]

    same2 --> pres[Present]
    prev2 --> pft[Perfect]

    classDef plainwhite fill:#ffffff;
    classDef query fill:#fed8b1;
    classDef mood fill:#e6ffe3;
    classDef tense fill:#dfbbfb;


    class k1 mood;
    class k2 tense;
    class root plainwhite;
    class Q1 query;
    class Q2 query;
    class Q3a query;
    class Q3b query;
    class indic mood;
    class circumstancial plainwhite;
    class temporal plainwhite;
    class primary plainwhite;
    class secondary plainwhite;

    class indictens tense;
    class subj mood;

    class same1 plainwhite;
    class same2 plainwhite;
    class prev1 plainwhite;
    class prev2 plainwhite;

    class pres tense;
    class pft tense;
    class impft tense;
    class ppft tense;
```    