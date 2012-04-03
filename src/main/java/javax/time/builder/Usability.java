/*
 * Copyright (c) 2012, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javax.time.builder;

import javax.time.DayOfWeek;
import javax.time.LocalDate;
import javax.time.LocalDateTime;
import javax.time.LocalTime;
import javax.time.calendrical.DateAdjusters;

/**
 * Usability class for package.
 */
public final class Usability {

    public static void main(String[] args) {
        simpleCalendar();
        System.out.println("------");
        lookup();
        System.out.println("------");
        period();
    }

    private Usability() {
    }

    private static void simpleCalendar() {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        
        date = date.withDayOfMonth(1);
        System.out.println(date);
        
        int month = date.getMonthOfYear().getValue();
        date = date.with(DateAdjusters.previousOrCurrent(DayOfWeek.MONDAY));
        System.out.println(date);
        
        while (date.getMonthOfYear().getValue() <= month) {
            String row = "";
            for (int i = 0; i < 7; i++) {
                row += date.getDayOfMonth() + " ";
                date = date.plusDays(1);
            }
            System.out.println(row);
        }
    }

    private static void lookup() {
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        LocalDateTime dateTime = LocalDateTime.now();
//        System.out.println(LocalDateField.DAY_OF_MONTH.getDateRules().get(date));
//        System.out.println(LocalDateField.MONTH_OF_YEAR.getDateRules().get(date));
//        System.out.println(LocalDateField.YEAR.getDateRules().get(date));
//        System.out.println(QuarterYearField.QUARTER_OF_YEAR.getDateRules().get(date));
//        System.out.println(QuarterYearField.MONTH_OF_QUARTER.getDateRules().get(date));
//        System.out.println(QuarterYearField.DAY_OF_QUARTER.getDateRules().get(date));
        
        output(date, LocalDateField.DAY_OF_MONTH);
        output(date, LocalDateField.MONTH_OF_YEAR);
        output(date, LocalDateField.YEAR);
        output(date, QuarterYearField.QUARTER_OF_YEAR);
        output(date, QuarterYearField.MONTH_OF_QUARTER);
        output(date, QuarterYearField.DAY_OF_QUARTER);
        
        output(dateTime, LocalDateField.DAY_OF_MONTH);
        output(time, LocalTimeField.HOUR_OF_DAY);
        output(time, LocalTimeField.MINUTE_OF_HOUR);
    }

    protected static void output(LocalDate date, DateField field) {
        System.out.println(field + " " + date.get(field));
    }

    protected static void output(LocalDateTime dateTime, DateTimeField field) {
        System.out.println(field + " " + dateTime.get(field));
    }

    protected static void output(LocalTime time, TimeField field) {
        System.out.println(field + " " + time.get(field));
    }

    private static void period() {
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.now().plusDays(25367);
        System.out.println(LocalDateUnit.DAYS.between(date1, date2));
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        
        date1 = LocalDate.of(2012, 2, 20);
        date2 = LocalDate.of(2014, 2, 19);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        date2 = LocalDate.of(2014, 2, 20);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        date2 = LocalDate.of(2014, 2, 21);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        date2 = LocalDate.of(2010, 2, 19);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        date2 = LocalDate.of(2010, 2, 20);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
        date2 = LocalDate.of(2010, 2, 21);
        System.out.println(LocalDateUnit.YEARS.between(date1, date2));
    }

}
