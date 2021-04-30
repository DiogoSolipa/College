import sun.awt.image.ImageWatched;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Lesson {
    String data;
    String hora;
    LinkedList presencas;
    LinkedList faltas;
    int nFaltas;

    Lesson(String data, String hora)
    {
        this.data = data;
        this.hora = hora;
        this.nFaltas = 0;
        this.presencas = new LinkedList();
        this.faltas = new LinkedList();
    }

    String getData()
    {
        return this.data;
    }

    String getHora() { return this.hora; }

    LinkedList getAlunos() { return this.presencas; }

    int get_nFaltas() { return this.nFaltas; }


    public boolean isDate(Lesson aula)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(aula.data.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public boolean isHour(Lesson aula)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(aula.hora.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        String s = "";
        System.out.println("PESSOAS QUE TIVERAM PRESENTES:");
        this.presencas.list_print(this.presencas);
        System.out.println("PESSOAS QUE FALTARAM:");
        this.faltas.list_print(this.faltas);
        System.out.println("AULA:");
        s+="Data:" + this.data+ " ; " + "Hora:" +  this.hora+ " ; " + "NºFaltas:" + this.nFaltas;
        return s;
    }
}
