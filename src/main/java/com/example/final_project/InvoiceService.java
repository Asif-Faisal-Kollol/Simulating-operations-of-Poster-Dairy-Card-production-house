package com.example.final_project;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class InvoiceService {
        private static final String FILE_NAME = "invoice.bin";

        public static ObservableList<Invoice> loadInvoices() {
            ObservableList<Invoice> invoices = FXCollections.observableArrayList();
            File file = new File(FILE_NAME);
            if (file.exists() && file.length() > 0) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            Invoice invoice = (Invoice) ois.readObject();
                            invoices.add(invoice);
                        } catch (EOFException e) {
                            break;
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return invoices;
        }

        public static void saveInvoice(Invoice invoice) {
            File file = new File(FILE_NAME);
            try (FileOutputStream fos = new FileOutputStream(file, true);
                 ObjectOutputStream oos = file.exists() && file.length() > 0
                         ? new AppendableObjectOutputStream(fos)
                         : new ObjectOutputStream(fos)) {
                oos.writeObject(invoice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
