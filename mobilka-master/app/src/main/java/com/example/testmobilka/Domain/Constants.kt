package com.example.testmobilka.Domain

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest

object Constants {
    var supabase = createSupabaseClient(
        supabaseUrl = "https://snygsrnitisuxaailkwb.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNueWdzcm5pdGlzdXhhYWlsa3diIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3MjA0NDgsImV4cCI6MjA1NTI5NjQ0OH0.c6FOoXoPYC8lVwVJKfcN6lnlPjWiJAEDnfB_N_NigwY"
    ) {
        install(Auth)
        install(Postgrest)
    }
}